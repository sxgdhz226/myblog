package top.fzqblog.utils;

public class Data
{
    private String city;

    private String country;

    private String ip;

    private String lat;

    private String lng;

    private String operator;

    private String province;

    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){
        return this.city;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public String getCountry(){
        return this.country;
    }
    public void setIp(String ip){
        this.ip = ip;
    }
    public String getIp(){
        return this.ip;
    }
    public void setLat(String lat){
        this.lat = lat;
    }
    public String getLat(){
        return this.lat;
    }
    public void setLng(String lng){
        this.lng = lng;
    }
    public String getLng(){
        return this.lng;
    }
    public void setOperator(String operator){
        this.operator = operator;
    }
    public String getOperator(){
        return this.operator;
    }
    public void setProvince(String province){
        this.province = province;
    }
    public String getProvince(){
        return this.province;
    }
	@Override
	public String toString() {
		return "Data [city=" + city + ", country=" + country + ", ip=" + ip + ", lat=" + lat + ", lng=" + lng
				+ ", operator=" + operator + ", province=" + province + "]";
	}
    
}
