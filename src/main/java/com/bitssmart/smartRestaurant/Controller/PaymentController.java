package com.bitssmart.smartRestaurant.Controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bitssmart.smartRestaurant.Model.FoodOrder;
import com.bitssmart.smartRestaurant.Model.OrderStatus;
import com.bitssmart.smartRestaurant.Model.OrderType;
import com.bitssmart.smartRestaurant.Model.Payment;
import com.bitssmart.smartRestaurant.Model.PaymentOptions;
import com.bitssmart.smartRestaurant.ResponseVO.ShowOrderVO;
import com.bitssmart.smartRestaurant.Service.OrderService;
import com.bitssmart.smartRestaurant.Service.PaymentService;
import com.paytm.pg.merchant.PaytmChecksum;
import org.springframework.core.env.Environment;

@Controller
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PaytmDetailPojo paytmDetailPojo;
	@Autowired
	private Environment env;
	
	@RequestMapping(value="/success", method=RequestMethod.POST)    
	public ModelAndView paymentSuccess(@ModelAttribute("orderid") long orderid )
	{
		FoodOrder foodOrder =orderService.getFoodOrder(orderid);
		foodOrder.setPayment(new Payment());
		foodOrder.getPayment().setFoodOrder(foodOrder);
		//Payment payment = new Payment();
		foodOrder.getPayment().setFoodOrder(foodOrder);
		//foodOrder.getPayment().setPaymentOptions(PaymentOptions.CASH);
		foodOrder.getPayment().setAmount(foodOrder.getTotalPrice());
		foodOrder.setIsPaid(true);
		if(foodOrder.getOrderType().equals(OrderType.HOME_DELIVERY)) {
			Random random = new Random(); 
			foodOrder.setOtp(String.format("%04d", random.nextInt(10000)));
		}
		paymentService.savePaymentDetails(foodOrder.getPayment());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("orderid",orderid);
		modelAndView.setViewName("paymentSuccess");
		
		return modelAndView; 
	} 
	
	@RequestMapping(value="/trackOrder", method=RequestMethod.POST)    
	public ModelAndView trachOrder(@ModelAttribute("orderid") long orderid )
	{
		FoodOrder foodOrder =orderService.getFoodOrder(orderid);
		ModelAndView modelAndView = new ModelAndView();
		if(foodOrder.getOrderStatus().equals(OrderStatus.IS_COOKING))
			modelAndView.addObject("msg", "Restaurant is preparing your order!");
		else if(foodOrder.getOrderStatus().equals(OrderStatus.PICKED_UP))
			modelAndView.addObject("msg", "Your Order has been Picked up!");
		else
			modelAndView.addObject("msg", "Your order is "+foodOrder.getOrderStatus());
		
		if(foodOrder.getOrderType().equals(OrderType.HOME_DELIVERY)) {
			modelAndView.addObject("otp", null != foodOrder.getOtp()? foodOrder.getOtp(): "0000");
		}
		modelAndView.addObject("orderid",orderid);
		modelAndView.setViewName("trackOrder");
		return modelAndView;
	}
	
	@PostMapping(value = "/submitPaymentDetail")
	    public ModelAndView getRedirect(@RequestParam(name = "CUST_ID") String customerId,
	                                    @RequestParam(name = "TXN_AMOUNT") String transactionAmount,
	                                    @RequestParam(name = "ORDER_ID") String orderId) throws Exception {

	        ModelAndView modelAndView = new ModelAndView("redirect:" + paytmDetailPojo.getPaytmUrl());
	        TreeMap<String, String> parameters = new TreeMap<>();
	        paytmDetailPojo.getDetails().forEach((k, v) -> parameters.put(k, v));
	        parameters.put("MOBILE_NO", env.getProperty("paytm.mobile"));
	        parameters.put("EMAIL", env.getProperty("paytm.email"));
	        parameters.put("ORDER_ID", orderId);
	        parameters.put("TXN_AMOUNT", transactionAmount);
	        parameters.put("CUST_ID", customerId);
	        String checkSum = getCheckSum(parameters);
	        parameters.put("CHECKSUMHASH", checkSum);
	        modelAndView.addAllObjects(parameters);
	        return modelAndView;
	    }
	 
	 
	 @PostMapping(value = "/pgresponse")
	    public String getResponseRedirect(HttpServletRequest request, Model model) {

	        Map<String, String[]> mapData = request.getParameterMap();
	        TreeMap<String, String> parameters = new TreeMap<String, String>();
	        String paytmChecksum = "";
	        for (Entry<String, String[]> requestParamsEntry : mapData.entrySet()) {
	            if ("CHECKSUMHASH".equalsIgnoreCase(requestParamsEntry.getKey())){
	                paytmChecksum = requestParamsEntry.getValue()[0];
	            } else {
	            	parameters.put(requestParamsEntry.getKey(), requestParamsEntry.getValue()[0]);
	            }
	        }
	        String result;

	        boolean isValideChecksum = false;
	        System.out.println("RESULT : "+parameters.toString());
	        try {
	            isValideChecksum = validateCheckSum(parameters, paytmChecksum);
	            if (isValideChecksum && parameters.containsKey("RESPCODE")) {
	                if (parameters.get("RESPCODE").equals("01")) {
	                    result = "Payment Successful";
	                    FoodOrder foodOrder =orderService.getFoodOrder(Long.parseLong(parameters.get("ORDERID")));
	            		foodOrder.setPayment(new Payment());
	            		foodOrder.getPayment().setFoodOrder(foodOrder);
	            		//Payment payment = new Payment();
	            		foodOrder.getPayment().setFoodOrder(foodOrder);
	            		foodOrder.getPayment().setPaymentMode(parameters.get("PAYMENTMODE"));
	            		foodOrder.getPayment().setAmount(foodOrder.getTotalPrice());
	            		foodOrder.getPayment().setBankTXNID(parameters.get("BANKTXNID"));
	            		foodOrder.getPayment().setCurrency(parameters.get("CURRENCY"));
	            		foodOrder.getPayment().setGatewayName(parameters.get("GATEWAYNAME"));
	            		foodOrder.getPayment().setPaymentId(parameters.get("TXNID"));
	            		foodOrder.getPayment().setRepMsg(parameters.get("RESPMSG"));
	            		foodOrder.getPayment().setRespCode(parameters.get("RESPCODE"));
	            		foodOrder.getPayment().setTxnAmount(parameters.get("TXNAMOUNT"));
	            		
	            		foodOrder.getPayment().setTxnDate(parameters.get("TXNDATE"));
	            		foodOrder.getPayment().setStatus(parameters.get("STATUS"));
	            		
	            		foodOrder.setIsPaid(true);
	            		if(foodOrder.getOrderType().equals(OrderType.HOME_DELIVERY)) {
	            			Random random = new Random(); 
	            			foodOrder.setOtp(String.format("%04d", random.nextInt(10000)));
	            		}
	            		paymentService.savePaymentDetails(foodOrder.getPayment());
	            		model.addAttribute("orderid",parameters.get("ORDERID"));
	            		return "paymentSuccess";
	                } else {
	                    result = "Payment Failed";
	                    model.addAttribute("msg","Payment Failed.Please try again.");
	                    return "redirect:/showOrderBill?orderid="+parameters.get("ORDERID");
	                }
	            } else {
	                result = "Checksum mismatched";
	            }
	        } catch (Exception e) {
	            result = e.toString();
	        }
	        model.addAttribute("result",result);
	        parameters.remove("CHECKSUMHASH");
	        model.addAttribute("parameters",parameters);
	        return "report";
	    }

	    private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
	        return PaytmChecksum.verifySignature(parameters,
	                paytmDetailPojo.getMerchantKey(), paytmChecksum);
	    }


	private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
		return PaytmChecksum.generateSignature(parameters, paytmDetailPojo.getMerchantKey());
	}
	
	
	
}	


