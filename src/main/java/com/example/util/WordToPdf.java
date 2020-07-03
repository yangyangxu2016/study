package com.example.util;

import com.aspose.cells.License;
import com.aspose.words.Document;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * word文档 转换 PDF
 */
public class WordToPdf {

    /**
     * 获取license许可凭证
     *
     * @return
     */
    private static boolean getLicense() {
        boolean result = false;
        try {
            String licenseStr = "<License>\n"
                    + " <Data>\n"
                    + " <Products>\n"
                    + " <Product>Aspose.Total for Java</Product>\n"
                    + " <Product>Aspose.Words for Java</Product>\n"
                    + " </Products>\n"
                    + " <EditionType>Enterprise</EditionType>\n"
                    + " <SubscriptionExpiry>20991231</SubscriptionExpiry>\n"
                    + " <LicenseExpiry>20991231</LicenseExpiry>\n"
                    + " <SerialNumber>23dcc79f-44ec-4a23-be3a-03c1632404e9</SerialNumber>\n"
                    + " </Data>\n"
                    + " <Signature>0nRuwNEddXwLfXB7pw66G71MS93gW8mNzJ7vuh3Sf4VAEOBfpxtHLCotymv1PoeukxYe31K441Ivq0Pkvx1yZZG4O1KCv3Omdbs7uqzUB4xXHlOub4VsTODzDJ5MWHqlRCB1HHcGjlyT2sVGiovLt0Grvqw5+QXBuinoBY0suX0=</Signature>\n"
                    + "</License>";
            InputStream license = new ByteArrayInputStream(licenseStr.getBytes("UTF-8"));
            License asposeLic = new License();
            asposeLic.setLicense(license);
            System.out.println("zxu");
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * word文档  转换为 PDF
     *
     * @param inPath  源文件
     * @param outPath 目标文件
     */
    public static void doc2pdf(String inPath, String outPath) {

        //验证License，获取许可凭证
        if (!getLicense()) {
            return;
        }

        try {

            //新建一个PDF文档
            File file = new File(outPath);
            if (!file.exists()) {
                //先得到文件的上级目录，并创建上级目录，在创建文件
                file.getParentFile().mkdir();
                try {
                    //创建文件
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //新建一个IO输出流
            FileOutputStream os = new FileOutputStream(file);
            //获取将要被转化的word文档
            Document doc = new Document(inPath);
            // 全面支持DOC, DOCX,OOXML, RTF HTML,OpenDocument,PDF, EPUB, XPS,SWF 相互转换
            doc.save(os, com.aspose.words.SaveFormat.PDF);
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        doc2pdf("D:\\DCV升级数据迁移说明.docx", "D:\\DCV升级数据迁移说明.pdf");
    }

}
