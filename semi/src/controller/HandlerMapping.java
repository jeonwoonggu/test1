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
		} else if(command.equals("deleteMember")){
			controller=new DeleteMemberControll();
		} else if(command.equals("search")){
			controller=new SearchController();
		} else if(command.equals("wrtieReply")){
			controller=new WriteReplyController();
		} else if(command.equals("deleteReply")){
			controller = new DeleteReplyController();
		} else if(command.equals("updateReply")){
			controller = new UpdateReplyController();
		} else if(command.equals("forgetId")){
			controller= new ForgetIdController();
		} else if(command.equals("forgetpassword")){
			controller = new ForgetPasswordController();
		} else if(command.equals("changePassword")){
			controller = new ChangePasswordController();
		}else if(command.equals("deletePosting")){
			controller=new DeletePostingController();
		}else if(command.equals("updateView")){
			controller=new UpdateViewController();
		}else if(command.equals("updatePosting")){
			controller=new UpdatePostingController();
		}else if(command.equals("showContentNotHit")){
			controller=new ShowContentNotHitController();
		} else if (command.equals("like")) {
			controller = new LikeCountController();
		} else if (command.equals("likeCancel")){
			controller = new LikeCancelController();
		}
		return controller;
	}
}
