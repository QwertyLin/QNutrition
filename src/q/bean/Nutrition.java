package q.bean;

public class Nutrition {
	
	private int 
		amount, 
		amountPerTime,
		timePerDay;
	private String 
		name, 
		des;
	
	public int getTimes(){
		return getAmount() / getAmountPerTime();
	}
	
	public int getDays(){
		return getTimes() / getTimePerDay();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getAmountPerTime() {
		return amountPerTime;
	}
	public void setAmountPerTime(int amountPerTime) {
		this.amountPerTime = amountPerTime;
	}

	public int getTimePerDay() {
		return timePerDay;
	}

	public void setTimePerDay(int timePerDay) {
		this.timePerDay = timePerDay;
	}
	
	

}
