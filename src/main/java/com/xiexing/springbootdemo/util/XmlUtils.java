package com.xiexing.springbootdemo.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.springframework.core.annotation.AnnotationUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.lang.annotation.Annotation;

public final class XmlUtils {

    private static XStream xstream;

    static {
        setXstream(new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_"))));
        getXstream().autodetectAnnotations(true);
    }

    public static XStream getXstream() {
        return xstream;
    }

    public static void setXstream(XStream xstream) {
        XmlUtils.xstream = xstream;
    }

    /**
     * 对象转换成XML(默认utf8)
     * @param obj
     * @return
     */
    public static String toXML(Object obj) {
        getXstream().aliasSystemAttribute(null, "class");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(outputStream, "UTF-8");
            getXstream().toXML(obj, writer);
            String xml = outputStream.toString("UTF-8");
            return xml;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object fromXML(String xml, Class responseClass) {

        Annotation annotation = responseClass.getAnnotation(XStreamAlias.class);
        String annotationValue = (String) AnnotationUtils.getValue(annotation);

        XStream xstream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
        xstream.autodetectAnnotations(true);

        xstream.alias(annotationValue, responseClass);

        return xstream.fromXML(xml);
    }


    /**
     * @description :Object转换成xmlStr
     */
    public static String objectToXml(Object obj, String encoding) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            // Marshaller.JAXB_FORMATTED_OUTPUT 决定是否在转换成xml时同时进行格式化（即按标签自动换行，否则即是一行的xml）
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // Marshaller.JAXB_ENCODING xml的编码方式
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @description :xmlStr -> Object
     */
    @SuppressWarnings("unchecked")
    public static Object xmlToObject(String xmlStr, Class<?> clazz) {
        StringReader reader = null;
        Object xmlObject;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            // 进行将Xml转成对象的核心接口
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            reader = new StringReader(xmlStr);
            xmlObject = unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return xmlObject;
    }
}
