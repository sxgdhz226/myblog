package top.fzqblog.utils;
/**
 * 
 * @author Administrator
 *
 */
public class Point {
	
	private Data data;

    private int error;

    private String msg;

    private String errNum;

    private String errMsg;

    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }
    public void setError(int error){
        this.error = error;
    }
    public int getError(){
        return this.error;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }

    public String getErrNum() {
        return errNum;
    }

    public void setErrNum(String errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
	public String toString() {
		return "Point [data=" + data + ", error=" + error + ", msg=" + msg + "]";
	}
    
    
    
}


