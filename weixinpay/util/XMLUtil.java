package com.cgmcomm.webapi.utils.weixinpay.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

/**
 * @date 2020/4/26 9:28
 * @Description xml处理
 * @Param
 */
@Slf4j
public class XMLUtil {
    
	
	/**
	 * 获取请求中的xml数据
	 * @param request
	 * @return
	 */
	public static String recieveData(HttpServletRequest request)
    {
        String inputLine = null;
        // 接收到的数据
        StringBuffer recieveData = new StringBuffer();
        BufferedReader in = null;
        try
        {
            in = new BufferedReader(new InputStreamReader(
                    request.getInputStream(), "UTF-8"));
            while ((inputLine = in.readLine()) != null)
            {
                recieveData.append(inputLine);
            }
        }catch (IOException e){
            log.error("[ wechatPay] xml 接受异常:{}", e.getMessage(),e);
        }finally {
            try
            {
                if (null != in)
                {
                    in.close();
                }
            } catch (IOException e) {
                log.error("[ wechatPay] xml 接受IOException异常:{}", e.getMessage(),e);
            }           
        }
        return recieveData.toString();
    }


    /**
     * 对象转xml
     * @param obj
     * @return
     */
    public static String ObjectToXML(Object obj) {
        //使用注解设置别名必须在使用之前加上注解类才有作用
        xstream.processAnnotations(obj.getClass());
        return xstream.toXML(obj);
    }

    /**
     * 扩展xstream，使其支持CDATA块
     */
    private static XStream xstream = new XStream(new XppDriver(new NoNameCoder()) {

        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @Override
                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                @Override
                public String encodeNode(String name) {
                    return name;
                }


                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
	
	
}
