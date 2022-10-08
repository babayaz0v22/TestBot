package uz.test.bot.web.rest.vm;

import java.util.List;

public class ExpessVM {
        public boolean hasError;
        public String type;
        public List<DirectionVM> direction;
        public Object showWithoutPlaces;
        public String reqLocalSend;
        public String reqLocalRecv;
        public String reqAddress;
        public String reqExpressDateTime;
        public String reqExpressZK;
        public String content;
        public Object code;

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DirectionVM> getDirection() {
        return direction;
    }

    public void setDirection(List<DirectionVM> direction) {
        this.direction = direction;
    }

    public Object getShowWithoutPlaces() {
        return showWithoutPlaces;
    }

    public void setShowWithoutPlaces(Object showWithoutPlaces) {
        this.showWithoutPlaces = showWithoutPlaces;
    }

    public String getReqLocalSend() {
        return reqLocalSend;
    }

    public void setReqLocalSend(String reqLocalSend) {
        this.reqLocalSend = reqLocalSend;
    }

    public String getReqLocalRecv() {
        return reqLocalRecv;
    }

    public void setReqLocalRecv(String reqLocalRecv) {
        this.reqLocalRecv = reqLocalRecv;
    }

    public String getReqAddress() {
        return reqAddress;
    }

    public void setReqAddress(String reqAddress) {
        this.reqAddress = reqAddress;
    }

    public String getReqExpressDateTime() {
        return reqExpressDateTime;
    }

    public void setReqExpressDateTime(String reqExpressDateTime) {
        this.reqExpressDateTime = reqExpressDateTime;
    }

    public String getReqExpressZK() {
        return reqExpressZK;
    }

    public void setReqExpressZK(String reqExpressZK) {
        this.reqExpressZK = reqExpressZK;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }
}

