package top.fzqblog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.mapper.ExamDetailMapper;
import top.fzqblog.mapper.ExamMapper;
import top.fzqblog.po.enums.ExamChooseType;
import top.fzqblog.po.enums.PageSize;
import top.fzqblog.po.enums.StatusEnum;
import top.fzqblog.po.enums.TextLengthEnum;
import top.fzqblog.po.model.Exam;
import top.fzqblog.po.model.ExamDetail;
import top.fzqblog.po.query.ExamQuery;
import top.fzqblog.service.ExamService;
import top.fzqblog.utils.Constants;
import top.fzqblog.utils.Page;
import top.fzqblog.utils.PageResult;
import top.fzqblog.utils.StringUtils;
@Service
public class ExamServiceImpl implements ExamService{
	@Autowired
	private ExamMapper<Exam, ExamQuery> examMapper;
	
	@Autowired
	private ExamDetailMapper<ExamDetail, ExamQuery> examDetailMapper;
	
	private final int MIN_ANSWER_COUNT = 2;
	private final int MAX_ANSWER_COUNT = 10;
	private final int MAX_RIGHT_ANSWER_COUNT = 1;
	private final int RIGHT_ANSWER = 1;
	private final int NOT_RIGHT_ANSWER = 0;
	
	public List<Exam> findExamListRand(Integer categoryId) {
		if(categoryId != null  && categoryId == 0){
			categoryId = null;
		}
		ExamQuery examQuery = new ExamQuery();
		examQuery.setCategoryId(categoryId);
		examQuery.setExamMaxTitle(Constants.EXAM_MAX_TITLE);
		examQuery.setStatus(StatusEnum.AUDIT);
		examQuery.setShowAnalyse(Boolean.FALSE);
		return this.examMapper.selectExamRand(examQuery);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BussinessException.class)
	public void saveExam(Exam exam, String[] answers, Integer[] rightAnswers)
			throws BussinessException {
		if (exam.getCategoryId() == null
				|| StringUtils.isEmpty(exam.getExamTitle())
				|| exam.getExamTitle().length() > TextLengthEnum.TEXT
						.getLength()
				|| exam.getChooseType() == null
				|| answers == null
				|| answers.length < MIN_ANSWER_COUNT
				|| answers.length > MAX_ANSWER_COUNT
				|| (rightAnswers.length > MAX_RIGHT_ANSWER_COUNT && exam
						.getChooseType() == ExamChooseType.SingleSelection)
				|| rightAnswers.length == answers.length
				|| (!StringUtils.isEmpty(exam.getAnalyse()) && exam
						.getAnalyse().length() > TextLengthEnum.TEXT
						.getLength())) {
			throw new BussinessException("参数错误");
		}
		int answers_length = answers.length;
		for(Integer index : rightAnswers){
			if(index < 0 || index > answers_length){
				throw new BussinessException("参数错误");
			}
		}
		exam.setCreateTime(new Date());
		exam.setStatus(StatusEnum.INIT);
		this.examMapper.insert(exam);
		Map<Integer, Integer> rightAnswerMap = new HashMap<Integer, Integer>();
		for(Integer rightanswer : rightAnswers){
			rightAnswerMap.put(rightanswer, rightanswer);
			}
		List<ExamDetail> examDetails = new ArrayList<ExamDetail>();
		for(int i = 0; i < answers_length; i++){
			if(StringUtils.isEmpty(answers[i]) || answers[i].length() > TextLengthEnum.TEXT.getLength()){
				throw new BussinessException("参数错误");
			}
			ExamDetail examDetail = new ExamDetail();
			examDetail.setExamId(exam.getId());
			examDetail.setAnswer(answers[i]);
			if(rightAnswerMap.get(i) != null){
				examDetail.setIsRightAnswer(RIGHT_ANSWER);
			}
			else {
				examDetail.setIsRightAnswer(NOT_RIGHT_ANSWER);
			}
			examDetails.add(examDetail);
		}
		if(examDetails.isEmpty()){
			throw new BussinessException("参数错误");
		}
		this.examDetailMapper.insertBatch(examDetails);
	}

	public List<Exam> doMark(String examIds, String rightAnswers)
			throws BussinessException {
		if(StringUtils.isEmpty(examIds) || StringUtils.isEmpty(rightAnswers)){
			throw new BussinessException("参数错误");
		}
		String[] examids = examIds.split(",");
		String[] rightanswers = rightAnswers.split(",");
		if(examids == null || rightanswers == null || examids.length > Constants.EXAM_MAX_TITLE){
			throw new BussinessException("参数错误");
		}
		Map<String, String> rightAnswerMap = new HashMap<String, String>();
		for(String rightanswer : rightanswers){
			rightAnswerMap.put(rightanswer, rightanswer);
		}
		ExamQuery examQuery = new ExamQuery();
		examQuery.setStatus(StatusEnum.AUDIT);
		examQuery.setShowAnalyse(Boolean.TRUE);
		examQuery.setExamIds(examids);
		List<Exam> examsWithRightAnswer = this.examMapper.selectListWithRightAnswer(examQuery);
		for(Exam exam : examsWithRightAnswer){
			List<ExamDetail> examDetails = exam.getExamDetails();
			List<Integer> correctAnswerIds = new ArrayList<Integer>();
			exam.setCorrectAnswerIds(correctAnswerIds);
			boolean isCorrect = Boolean.TRUE;
			for(ExamDetail detail : examDetails){
				if(detail.getIsRightAnswer() == RIGHT_ANSWER){
					correctAnswerIds.add(detail.getId());
					if(rightAnswerMap.get(detail.getId().toString()) == null){
						isCorrect = false;
					}
				}
				else if(rightAnswerMap.get(detail.getId().toString()) != null){
					isCorrect = false;
				}
			}
			exam.setCorrect(isCorrect);
		}
		System.out.println(examsWithRightAnswer);
		return examsWithRightAnswer;
	}

	public PageResult<Exam> findExamUsers(ExamQuery examQuery) {
		int count = this.examMapper.selectExamUsersCount(examQuery);
		int size = PageSize.PAGE_SIZE20.getSize();
		int pageNum = 1;
		if(examQuery.getPageNum() != 1){
			pageNum = examQuery.getPageNum();
		}
		Page page = new Page(pageNum, count, size);
		examQuery.setPage(page);
		List<Exam> list = this.examMapper.selectExamUsers(examQuery);
		PageResult<Exam> pageResult = new PageResult<Exam>(page, list);
		return pageResult;
	}

	
	@Override
	public List<Exam> findExamWithRightAnswer() {
		ExamQuery examQuery = new ExamQuery();
		examQuery.setStatus(StatusEnum.INIT);
		examQuery.setShowAnalyse(Boolean.TRUE);
		List<Exam> examsWithRightAnswer = this.examMapper.selectListWithRightAnswer(examQuery);
		return examsWithRightAnswer;
	}
	
	@Override
	public void deleteBatch(Integer[] ids) throws BussinessException {
		if(ids == null){
			throw new BussinessException("参数错误");
		}
		
		for(int id : ids){
			examMapper.delete(id);
			examDetailMapper.delete(id);
		}
	}

	@Override
	public void updateStatusBatch(Integer[] ids) throws BussinessException {
		if(ids == null){
			throw new BussinessException("参数错误");
		}
		
		examMapper.updateExamStatus(StatusEnum.AUDIT, ids);
		
	}
}
