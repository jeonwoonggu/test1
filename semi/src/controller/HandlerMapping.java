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
		}else if (command.equals("login")) {
			controller = new LoginController();
		} else if (command.equals("logout")) {
			controller = new LogoutController();
		} else if (command.equals("registration")) {
			controller = new RegistrationController();
		} else if (command.equals("findMemberId")) {
			controller = new FindMemberIdController();
		} else if (command.equals("findNickName")){
			controller = new FindNickName();
		} else if (command.equals("myInfo")){
			controller = new MyInfoController();
		} else if(command.equals("showContent")){
			controller = new ShowContentController();
		}else if (command.equals("categoryList")){
			controller = new CategoryListController();
		} else if (command.equals("hitsList")){
			controller = new HitsListController();
		} else if (command.equals("sympathyList")){
			controller = new SympathyListController();
		} else if(command.equals("updateMyInfo")){
			controller=new UpdateMyInfoController();
		}else if(command.equals("search")){
			controller=new SearchController();
		}
		return controller;
	}
}










