package com.xiexing.springbootdemo.entity;

import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@ToString
public class AppAdvice implements Serializable {

    private static final long serialVersionUID = -6886961127397786924L;

    private BigDecimal wfiAppAdviceId;

    private String apprOpt;

    private String invDoc;

    private String invOpt;

    private String invReq;

    // 审批结论
    private String appConclusion;

    private String fraudTyp;

    private String fraudKind;

    private String fraudPart;

    private String fraudDealer;

    private String fraudBlk;

    private String rejectRsn;

    private String appAdvice;

    private String appOutAdvice;

    private String appInAdvice;

    private String wfiInstanceId;

    private String wfiNodeId;

    private String wfiNodeName;

    private String wfiSceneId;

    private String wfsign;

    private String appUser;

    private String appOrg;

    private String operateTime;

    private String remark;

    private String wfiStatus;

    private String wfiProTyp;

    private String wfiNodeSign;

    private String wfiNodeSeq;

    private String appOrgName;

    private String appUserName;

    private String dealUsr;

    private String callRpt;

    private String gutrOpt;

    private BigDecimal maxApprvAmt;

    private BigDecimal maxApprvTnr;

    private BigDecimal minFstPay;

    private String daybookInd;

    private String isCheat;

    private String unitRsn;

    private BigDecimal assCarPrice;

    private String tail;

    private String intend;

    private String relation;

    private String addInfo;

    private String other;

    private String appSupplementAdvice;

    private String gpsCount;

    private String usrName;

    private String applyAmt;

    private String apprvTnr;

    private String fstPct;
    private String salerName;

    private String salerMobile;

    private String bchDesc;

    public String getFstPct() {
        return fstPct;
    }

    public void setFstPct(String fstPct) {
        this.fstPct = fstPct == null ? null : fstPct.trim();
    }


    public String getApprvTnr() {
        return apprvTnr;
    }

    public void setApprvTnr(String apprvTnr) {
        this.apprvTnr = apprvTnr == null ? null : apprvTnr.trim();
    }

    public String getApplyAmt() {
        return applyAmt;
    }

    public void setApplyAmt(String applyAmt) {
        this.applyAmt = applyAmt == null ? null : applyAmt.trim();
    }


    public BigDecimal getWfiAppAdviceId() {
        return wfiAppAdviceId;
    }

    public void setWfiAppAdviceId(BigDecimal wfiAppAdviceId) {
        this.wfiAppAdviceId = wfiAppAdviceId;
    }

    public String getApprOpt() {
        return apprOpt;
    }

    public void setApprOpt(String apprOpt) {
        this.apprOpt = apprOpt == null ? null : apprOpt.trim();
    }

    public String getInvDoc() {
        return invDoc;
    }

    public void setInvDoc(String invDoc) {
        this.invDoc = invDoc == null ? null : invDoc.trim();
    }

    public String getInvOpt() {
        return invOpt;
    }

    public void setInvOpt(String invOpt) {
        this.invOpt = invOpt == null ? null : invOpt.trim();
    }

    public String getInvReq() {
        return invReq;
    }

    public void setInvReq(String invReq) {
        this.invReq = invReq == null ? null : invReq.trim();
    }

    public String getAppConclusion() {
        return appConclusion;
    }

    public void setAppConclusion(String appConclusion) {
        this.appConclusion = appConclusion == null ? null : appConclusion.trim();
    }

    public String getFraudTyp() {
        return fraudTyp;
    }

    public void setFraudTyp(String fraudTyp) {
        this.fraudTyp = fraudTyp == null ? null : fraudTyp.trim();
    }

    public String getFraudKind() {
        return fraudKind;
    }

    public void setFraudKind(String fraudKind) {
        this.fraudKind = fraudKind == null ? null : fraudKind.trim();
    }

    public String getFraudPart() {
        return fraudPart;
    }

    public void setFraudPart(String fraudPart) {
        this.fraudPart = fraudPart == null ? null : fraudPart.trim();
    }

    public String getFraudDealer() {
        return fraudDealer;
    }

    public void setFraudDealer(String fraudDealer) {
        this.fraudDealer = fraudDealer == null ? null : fraudDealer.trim();
    }

    public String getFraudBlk() {
        return fraudBlk;
    }

    public void setFraudBlk(String fraudBlk) {
        this.fraudBlk = fraudBlk == null ? null : fraudBlk.trim();
    }

    public String getRejectRsn() {
        return rejectRsn;
    }

    public void setRejectRsn(String rejectRsn) {
        this.rejectRsn = rejectRsn == null ? null : rejectRsn.trim();
    }

    public String getAppAdvice() {
        return appAdvice;
    }

    public void setAppAdvice(String appAdvice) {
        this.appAdvice = appAdvice == null ? null : appAdvice.trim();
    }

    public String getAppOutAdvice() {
        return appOutAdvice;
    }

    public void setAppOutAdvice(String appOutAdvice) {
        this.appOutAdvice = appOutAdvice == null ? null : appOutAdvice.trim();
    }

    public String getAppInAdvice() {
        return appInAdvice;
    }

    public void setAppInAdvice(String appInAdvice) {
        this.appInAdvice = appInAdvice == null ? null : appInAdvice.trim();
    }

    public String getWfiInstanceId() {
        return wfiInstanceId;
    }

    public void setWfiInstanceId(String wfiInstanceId) {
        this.wfiInstanceId = wfiInstanceId == null ? null : wfiInstanceId.trim();
    }

    public String getWfiNodeId() {
        return wfiNodeId;
    }

    public void setWfiNodeId(String wfiNodeId) {
        this.wfiNodeId = wfiNodeId == null ? null : wfiNodeId.trim();
    }

    public String getWfiNodeName() {
        return wfiNodeName;
    }

    public void setWfiNodeName(String wfiNodeName) {
        this.wfiNodeName = wfiNodeName == null ? null : wfiNodeName.trim();
    }

    public String getWfiSceneId() {
        return wfiSceneId;
    }

    public void setWfiSceneId(String wfiSceneId) {
        this.wfiSceneId = wfiSceneId == null ? null : wfiSceneId.trim();
    }

    public String getWfsign() {
        return wfsign;
    }

    public void setWfsign(String wfsign) {
        this.wfsign = wfsign == null ? null : wfsign.trim();
    }

    public String getAppUser() {
        return appUser;
    }

    public void setAppUser(String appUser) {
        this.appUser = appUser == null ? null : appUser.trim();
    }

    public String getAppOrg() {
        return appOrg;
    }

    public void setAppOrg(String appOrg) {
        this.appOrg = appOrg == null ? null : appOrg.trim();
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime == null ? null : operateTime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getWfiStatus() {
        return wfiStatus;
    }

    public void setWfiStatus(String wfiStatus) {
        this.wfiStatus = wfiStatus == null ? null : wfiStatus.trim();
    }

    public String getWfiProTyp() {
        return wfiProTyp;
    }

    public void setWfiProTyp(String wfiProTyp) {
        this.wfiProTyp = wfiProTyp == null ? null : wfiProTyp.trim();
    }

    public String getWfiNodeSign() {
        return wfiNodeSign;
    }

    public void setWfiNodeSign(String wfiNodeSign) {
        this.wfiNodeSign = wfiNodeSign == null ? null : wfiNodeSign.trim();
    }

    public String getWfiNodeSeq() {
        return wfiNodeSeq;
    }

    public void setWfiNodeSeq(String wfiNodeSeq) {
        this.wfiNodeSeq = wfiNodeSeq == null ? null : wfiNodeSeq.trim();
    }

    public String getAppOrgName() {
        return appOrgName;
    }

    public void setAppOrgName(String appOrgName) {
        this.appOrgName = appOrgName == null ? null : appOrgName.trim();
    }

    public String getAppUserName() {
        return appUserName;
    }

    public void setAppUserName(String appUserName) {
        this.appUserName = appUserName == null ? null : appUserName.trim();
    }

    public String getDealUsr() {
        return dealUsr;
    }

    public void setDealUsr(String dealUsr) {
        this.dealUsr = dealUsr == null ? null : dealUsr.trim();
    }

    public String getCallRpt() {
        return callRpt;
    }

    public void setCallRpt(String callRpt) {
        this.callRpt = callRpt == null ? null : callRpt.trim();
    }

    public String getGutrOpt() {
        return gutrOpt;
    }

    public void setGutrOpt(String gutrOpt) {
        this.gutrOpt = gutrOpt == null ? null : gutrOpt.trim();
    }

    public BigDecimal getMaxApprvAmt() {
        return maxApprvAmt;
    }

    public void setMaxApprvAmt(BigDecimal maxApprvAmt) {
        this.maxApprvAmt = maxApprvAmt;
    }

    public BigDecimal getMaxApprvTnr() {
        return maxApprvTnr;
    }

    public void setMaxApprvTnr(BigDecimal maxApprvTnr) {
        this.maxApprvTnr = maxApprvTnr;
    }

    public BigDecimal getMinFstPay() {
        return minFstPay;
    }

    public void setMinFstPay(BigDecimal minFstPay) {
        this.minFstPay = minFstPay;
    }

    public String getDaybookInd() {
        return daybookInd;
    }

    public void setDaybookInd(String daybookInd) {
        this.daybookInd = daybookInd == null ? null : daybookInd.trim();
    }

    public String getIsCheat() {
        return isCheat;
    }

    public void setIsCheat(String isCheat) {
        this.isCheat = isCheat == null ? null : isCheat.trim();
    }

    public String getUnitRsn() {
        return unitRsn;
    }

    public void setUnitRsn(String unitRsn) {
        this.unitRsn = unitRsn == null ? null : unitRsn.trim();
    }

    public BigDecimal getAssCarPrice() {
        return assCarPrice;
    }

    public void setAssCarPrice(BigDecimal assCarPrice) {
        this.assCarPrice = assCarPrice;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail == null ? null : tail.trim();
    }

    public String getIntend() {
        return intend;
    }

    public void setIntend(String intend) {
        this.intend = intend == null ? null : intend.trim();
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation == null ? null : relation.trim();
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo == null ? null : addInfo.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    public String getAppSupplementAdvice() {
        return appSupplementAdvice;
    }

    public void setAppSupplementAdvice(String appSupplementAdvice) {
        this.appSupplementAdvice = appSupplementAdvice == null ? null : appSupplementAdvice.trim();
    }

    public String getGpsCount() {
        return gpsCount;
    }

    public void setGpsCount(String gpsCount) {
        this.gpsCount = gpsCount == null ? null : gpsCount.trim();
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName == null ? null : usrName.trim();
    }

    public String getBchDesc() {
        return bchDesc;
    }

    public void setBchDesc(String bchDesc) {
        this.bchDesc = bchDesc;
    }

    public String getSalerMobile() {
        return salerMobile;
    }

    public void setSalerMobile(String salerMobile) {
        this.salerMobile = salerMobile;
    }

    public String getSalerName() {
        return salerName;
    }

    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }
}