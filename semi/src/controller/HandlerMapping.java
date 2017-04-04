package controller;

public class HandlerMapping {
	private static HandlerMapping instance=new HandlerMapping();
	private HandlerMapping(){}
	public static HandlerMapping getInstance(){
		return instance;
	}
	public Controller create(String command){
		Controller controller=null;
		if(command.equals("list")){
			controller=new ListController();
		}else if (command.equals("write")) {
			controller = new WriteController();
		}
		return controller;
	}
}










