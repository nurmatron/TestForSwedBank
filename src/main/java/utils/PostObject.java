package utils;

public class PostObject {

    String code;
    String message;
    String uri;
    String method;

    public PostObject() {
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    public PostObject(String code, String message, String location, String uri, String method) {
        this.code = code;
        this.message = message;
        this.uri = uri;
        this.method = method;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "PostObject{" + "code='" + code + '\'' + ", message='" + message + '\'' + '\'' + ", uri='" + uri + '\'' + ", method='" + method + '\'' + '}';
    }
}
