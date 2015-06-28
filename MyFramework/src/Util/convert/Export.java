package Util.convert;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Model.po.News;

public class Export {
//	@RequestMapping(value="/exportHighChart.servlet",method = RequestMethod.POST)  
    public String exportWord(HttpServletRequest request,HttpServletResponse response) throws Exception{  
        String type = request.getParameter("type");  
        response.setContentType("application/octet-stream; charset=UTF-8");    
        if("word".equals(type)){  
            response.setHeader("content-disposition", "attachment;filename=" + new SimpleDateFormat("yyyyMMddHH:mm:ss").format(new Date()) + ".doc");  
        }else if("pdf".equals(type)){  
            response.setHeader("content-disposition", "attachment;filename=" + new SimpleDateFormat("yyyyMMddHH:mm:ss").format(new Date()) + ".pdf");  
        }  
        String svgCode = request.getParameter("svg");//highcharts图表svgCode  
        String svg [] = svgCode.split("_");  
        String path[] = new String[svg.length];  
        OutputStream out = response.getOutputStream();  
        ItextManager tm = ItextManager.getInstance();  
        List<News> newsList = getData();  
        List<String> imageList = new ArrayList<String>();  
          
        if(svg!=null){  
            for(int k=0;k<svg.length;k++){  
                String picName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+".png";  
                path[k] = request.getSession().getServletContext().getRealPath("/upload/"+picName);  
                imageList.add(path[k]);  
                SvgPngConverter.convertToPng(svg[k], path[k]);  
            }  
        }  
          
        tm.createRtfContext(newsList,imageList,out,type);  
          
        out.flush();  
        out.close();  
        return "success";  
    } 
	public List<News> getData() {  
        List<News> newsList = new ArrayList<News>();  
        News news1 = new News();  
        news1.setTitle("标题：国泰君安*公司研究*广发证券：定增完成，如虎添翼*000776*投资银行业与经纪业行业*梁静");  
        news1.setContent("正文：报告类型=公司事件点评公司简称=广发证券公司代码=000776报告日期=Thu Aug 25 09:05:29 CST 2011研究员 =梁静报告标题=定增完成，如虎添翼【报告摘要】8月25日，广发证券成功向揭阳市信宏资产、汇添富基金、上海海博鑫惠、兴业基金等10家机构定向增发4.526亿股、募集资金121.8亿元，募集资金净额120亿元。");  
//        news1.setPublishTime("发布时间：2014-05-12");  
          
        News news2 = new News();  
        news2.setTitle("标题：[申万销售夏敬慧] 基金仓位周报----开基仓位下降1.51%");  
        news2.setContent("正文：理财产品部分析师: 杨鹏（18930809297） 开基仓位有所下降：本周，开放式基金平均仓位继续下降。");  
//        news2.setPublishTime("发布时间：2014-05-25");  
          
        newsList.add(news1);  
        newsList.add(news2);  
          
        return newsList;  
    }  
}
