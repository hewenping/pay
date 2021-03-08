package com.pay.demo.wxpay;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class WXpayController {
    @RequestMapping("/wxPcIndex")
    public String index(){
        return "wxPcIndex";
    }

    @RequestMapping("/toWXPcPay")
    public void toPay(HttpServletRequest request, HttpServletResponse response){
        try {
            //商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = request.getParameter("WIDout_trade_no");
            //付款金额，必填
            String total_amount = request.getParameter("WIDtotal_amount");
            //订单名称，必填
            String subject = request.getParameter("WIDsubject");
            //商品描述，可空
            String body = request.getParameter("WIDbody");
            WXPayConfig config = new WXPayConfig();
            //WXPay wxpay = new WXPay(config);//正式
            WXPay wxpay = new WXPay(config,config.getNotifyUrl(),false,false);//沙箱
            Map<String, String> data = new HashMap<String, String>();
            data.put("body", subject);//商品描述
            data.put("detail", body);//商品详情
            data.put("out_trade_no", out_trade_no);//商户订单号
            data.put("device_info", "WEB");//设备号，PC网页或公众号内支付可以传"WEB"
            data.put("fee_type", "CNY");//标价币种，默认人民币：CNY
            data.put("total_fee", total_amount);//标价金额，单位为分，
            data.put("spbill_create_ip", "123.12.12.123");//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
            //data.put("notify_url", "http://www.example.com/wxpay/notify");//异步接收微信支付结果通知的回调地址，通知url必须为外网可
            data.put("trade_type", "NATIVE");  //交易类型   JSAPI：公众号支付，NATIVE：扫码支付，APP：APP支付 此处指定为扫码支付
            //data.put("product_id", "12");//trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
            Map<String, String> resp = wxpay.unifiedOrder(data);
            WXPayUtil.getLogger().info(resp.toString());
            if(resp.get("code_url")!=null){
                //生成二维码
                Map<EncodeHintType, Object>  hints=new HashMap<EncodeHintType, Object>();
                // 指定纠错等级
                hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                // 指定编码格式
                hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                hints.put(EncodeHintType.MARGIN, 1);
                BitMatrix bitMatrix = new MultiFormatWriter().encode(resp.get("code_url").toString(), BarcodeFormat.QR_CODE, 200, 200, hints);
                OutputStream out = response.getOutputStream();
                MatrixToImageWriter.writeToStream(bitMatrix, "png", out);//输出二维码
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @RequestMapping("/wxWrapIndex")
    public String wxWrapIndex(){
        return "wxWrapIndex";
    }

    //商户平台--"产品中心"--"开发配置"自行配置域名
    @RequestMapping("/toWXWrapPay")
    public void toWXWrapPay(HttpServletRequest request, HttpServletResponse response){
        try {
            //商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = request.getParameter("WIDout_trade_no");
            //付款金额，必填
            String total_amount = request.getParameter("WIDtotal_amount");
            //订单名称，必填
            String subject = request.getParameter("WIDsubject");
            //商品描述，可空
            String body = request.getParameter("WIDbody");
            WXPayConfig config = new WXPayConfig();
            //WXPay wxpay = new WXPay(config);//正式
            WXPay wxpay = new WXPay(config,config.getNotifyUrl(),false,false);//沙箱
            Map<String, String> data = new HashMap<String, String>();
            data.put("body", subject);//商品描述
            data.put("detail", body);//商品详情
            data.put("out_trade_no", out_trade_no);//商户订单号
            data.put("device_info", "MWEB");//设备号，PC网页或公众号内支付可以传"WEB"
            data.put("fee_type", "CNY");//标价币种，默认人民币：CNY
            data.put("total_fee", total_amount);//标价金额，单位为分，
            data.put("spbill_create_ip", "123.12.12.123");//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
            //data.put("notify_url", "http://www.example.com/wxpay/notify");//异步接收微信支付结果通知的回调地址，通知url必须为外网可
            data.put("trade_type", "MWEB");  //交易类型   JSAPI：公众号支付，NATIVE：扫码支付，APP：APP支付 此处指定为扫码支付
            //data.put("product_id", "12");//trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
            Map<String, String> resp = wxpay.unifiedOrder(data);
            WXPayUtil.getLogger().info(resp.toString());
            boolean signatureValid = WXPayUtil.isSignatureValid(resp, new WXPayConfig().getKey(), WXPayConstants.SignType.HMACSHA256);
            if(signatureValid) {//验证签名
                if(resp.get("mweb_url")!=null){
                    //如需返回至指定页面，则可以在MWEB_URL后拼接上redirect_url参数，来指定回调页面。
                    //需对redirect_url进行urlencode处理
                    //&redirect_url=https%3A%2F%2Fwww.wechatpay.com.cn
                    response.sendRedirect(resp.get("mweb_url"));
                    //request.getRequestDispatcher().forward(request, response);

                }
            }else{
                WXPayUtil.getLogger().info("接收微信支付系统异步回调的签名验证失败");
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }




    @RequestMapping("/wxPayNotify")
    public void wxPayNotify(HttpServletRequest request, HttpServletResponse response){

        try {
        InputStream inputStream;
        StringBuffer sb = new StringBuffer();
        inputStream = request.getInputStream();
        String s=null;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();
        WXPayUtil.getLogger().info("接收微信支付系统异步回调的参数："+sb.toString());
        Map<String, String> paramMap = WXPayUtil.xmlToMap(sb.toString());
        boolean signatureValid = WXPayUtil.isSignatureValid(paramMap, new WXPayConfig().getKey(), WXPayConstants.SignType.HMACSHA256);
        Map result= new HashMap<String,String>();
        if(signatureValid){//验证签名
            if("SUCCESS".equals(paramMap.get("return_code"))&&"SUCCESS".equals(paramMap.get("return_code"))){
                String out_trade_no = paramMap.get("out_trade_no");//商户订单号
                String total_fee = paramMap.get("total_fee");//订单金额，total_fee和cash_fee比较一下防止数额不对
                String cash_fee = paramMap.get("cash_fee");//实际支付金额
                //这里写数据更新代码



                result.put("return_code","SUCCESS");
                result.put("return_msg","OK");
            }else{
                result.put("return_code","FAIL");
                result.put("return_msg","参数错误");
                WXPayUtil.getLogger().info("接收微信支付系统异步回调的参数错误");
            }


        }else{
            result.put("return_code","FAIL");
            result.put("return_msg","签名验证错误");
            WXPayUtil.getLogger().info("接收微信支付系统异步回调的签名验证失败");
        }
        String resultXml = WXPayUtil.mapToXml(result);

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/xml;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(resultXml);
        out.flush();
        out.close();
        WXPayUtil.getLogger().info(resultXml);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

}
