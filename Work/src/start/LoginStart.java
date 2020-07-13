package start;

import java.util.List;

import control.CouponManager;
import control.DiscountManager;
import control.Full_distcountManager;
import control.MenuManager;
import control.OrderManager;
import control.ProductManager;
import control.TypeManager;
import control.UserManager;
import model.Coupon;
import model.Full_discount;

public class LoginStart {
	public static ProductManager productManager=new ProductManager();
	public static UserManager userManager=new UserManager();
	public static CouponManager couponManager=new CouponManager();
	public static DiscountManager dsiDiscountManager=new DiscountManager();
	public static Full_distcountManager full_distcountManager=new Full_distcountManager();
	public static OrderManager orderManager=new OrderManager();
	public static TypeManager typeManager=new TypeManager();
	public static MenuManager menuManager=new MenuManager();
}
