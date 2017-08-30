package my.hhx.com.newpager.util;

import java.util.List;

/**
 * Created by hhx on 2017/8/2.
 */

public class WebUtil {
    public static final String BASE_URL = "file:///android_asset/";
    public static final String MIME_TYPE = "text/html";
    public static final String ENCODING = "utf-8";
    public static final String ZHIHU_FAIL_URL = "http://daily.zhihu.com/";
    public static final String IT_FAIL_URL = "https://www.ithome.com/list/";

    private static final String CSS_LINK_PATTERN = " <link href=\"%s\" type=\"text/css\" rel=\"stylesheet\" />";
    private static final String NIGHT_DIV_TAG_START = "<div class=\"night\">";
    private static final String NIGHT_DIV_TAG_END = "</div>";
    private static final String IMG_URL_TAG = "<img.*src=";

    private static final String DIV_IMAGE_PLACE_HOLDER = "class=\"img-place-holder\"";
    private static final String DIV_IMAGE_PLACE_HOLDER_IGNORED = "class=\"img-place-holder-ignored\"";

    public static String buildHtmlWithCss(String html, List<String> cssUrls, boolean isNightMode) {
        StringBuilder result = new StringBuilder();
        for (String cssUrl : cssUrls) {
            result.append(String.format(CSS_LINK_PATTERN, cssUrl));
        }

        if (isNightMode) {
            result.append(NIGHT_DIV_TAG_START);
        }
        result.append(html.replace(DIV_IMAGE_PLACE_HOLDER, DIV_IMAGE_PLACE_HOLDER_IGNORED));
        if (isNightMode) {
            result.append(NIGHT_DIV_TAG_END);
        }
        String data = result.toString();
        //让文字不再散乱
        data = data.replace("<p", "<p style='text-align:justify' ");
        return data;
    }

    public static String buildHtmlForIt(String content, boolean isNightMode) {
        StringBuilder modifiedHtml = new StringBuilder();
        modifiedHtml.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.0//EN\" \"http://www.wapforum.org/DTD/xhtml-mobile10.dtd\">"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">" + "<head>" + "<meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml; charset=utf-8\"/>"
                + "<meta http-equiv=\"Cache-control\" content=\"public\" />" + "<meta name=\"viewport\" content=\"width=device-width,initial-scale=1,user-scalable=no,minimum-scale=1.0,maximum-scale=1.0\" />"
                + "<link rel=\"stylesheet\" href=\"file:///android_asset/news.css\" type=\"text/css\">"
                + "<script src=\"file:///android_asset/jquery.min.js\"></script>" + "<script src=\"file:///android_asset/info.js\"></script>");
        modifiedHtml.append("<body ");
        if (isNightMode) {
            modifiedHtml.append("class=\'night\'");
        }
        modifiedHtml.append(">");
        modifiedHtml.append(content);
        modifiedHtml.append("</body></html>");
        return modifiedHtml.toString();
    }

    public static List<String> getAllImgUrl(String html) {

    }
}
