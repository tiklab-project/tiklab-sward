package io.tiklab.sward.document.export.builder.html;

import io.tiklab.sward.document.export.model.html.HtmlImg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class HtmlBuilder {

    String htmlInit = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>${import_tiklab_html_title}</title>
            </head>
            <body>
                ${import_tiklab_html_body}
            </body>
            </html>
            """;

    private final StringBuilder bodyBuilder = new StringBuilder();
    private final StringBuilder titleBuilder = new StringBuilder();

    private final Logger logger = LoggerFactory.getLogger(HtmlBuilder.class);

    public static HtmlBuilder instance() {
        return new HtmlBuilder();
    }

    public void addTitle(String title) {
        titleBuilder.append(title);
    }

    public void addHead(String title,String index) {
        bodyBuilder.append("<h").append(index).append(">")
                .append(title)
                .append("</h").append(index).append(">");
    }


    public void addText(String text) {
        bodyBuilder.append(text);
    }

    public void addParagraph(String text) {
        bodyBuilder.append("<div>\n<p style=\"font-size:13px\">").append(text).append("</p>\n</div>");
    }

    public void addImage(HtmlImg img) {
        bodyBuilder.append("<div>\n<img src=\"").append(img.getPath())
                .append("\" alt=").append(img.getImgName())
                .append("\">\n</div>");
    }


    /**
     * 图片转base64
     * @param imagePath 图片路径
     * @return base64
     * @throws IOException 读取图片异常
     */
    public static String imageToBase64(String imagePath) throws IOException {
        byte[] bytes = Files.readAllBytes(new File(imagePath).toPath());
        String base64 = Base64.getEncoder().encodeToString(bytes);
        String mimeType = "image/png";
        return "data:" + mimeType + ";base64," + base64;
    }

    public void save(String filePath) throws IOException {

        String html = htmlInit
                .replace("${import_tiklab_html_title}", titleBuilder.toString())
                .replace("${import_tiklab_html_body}", bodyBuilder.toString());

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(html);
        }
    }

}
