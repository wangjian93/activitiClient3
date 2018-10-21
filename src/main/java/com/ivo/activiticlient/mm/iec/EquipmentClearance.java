package com.ivo.activiticlient.mm.iec;

import com.ivo.activiticlient.biz.Model;

import javax.persistence.*;

/**
 * @author wangjian
 * @date 2018/10/13
 */
@Entity
@Table(name = "PUR_M_EquipmentClearance")
public class EquipmentClearance extends Model {

    /**
     *
     */
    private static final long serialVersionUID = -8973488715797079112L;

    /**
     * 物流确认
     */
    private String logiticsConfirm;

    /**
     * 对外事务确认
     */
    private String erConfirm;

    /**
     * 采购确认
     */
    private String purConfirm1;
    private String purConfirm2;
    private String purConfirm3;
    private String purConfirm4;
    private String purConfirm5;
    private String purConfirm6;
    private String purConfirm7;
    private String purConfirm8;
    private String purConfirm9;

    /**
     * 回复
     */
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String answer5;
    private String answer6;
    private String answer7;
    private String answer8;
    private String answer9;
    private String answer10;
    private String answer11;
    private String answer12;
    private PoInformation poInformation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PoInformation_FK")
    public PoInformation getPoInformation(){
        return poInformation;
    }
    public void setPoInformation(PoInformation poInformation){
        this.poInformation = poInformation;
    }

    @Column(name = "LogiticsConfirm")
    public String getLogiticsConfirm() {
        return logiticsConfirm;
    }
    public void setLogiticsConfirm(String logiticsConfirm) {
        this.logiticsConfirm = logiticsConfirm;
    }

    @Column(name = "ERConfirm")
    public String getERConfirm() {
        return erConfirm;
    }
    public void setERConfirm(String eRConfirm) {
        erConfirm = eRConfirm;
    }

    @Column(name = "PurConfirm1")
    public String getPurConfirm1() {
        return purConfirm1;
    }
    public void setPurConfirm1(String purConfirm1) {
        this.purConfirm1 = purConfirm1;
    }

    @Column(name = "PurConfirm2")
    public String getPurConfirm2() {
        return purConfirm2;
    }
    public void setPurConfirm2(String purConfirm2) {
        this.purConfirm2 = purConfirm2;
    }

    @Column(name = "PurConfirm3")
    public String getPurConfirm3() {
        return purConfirm3;
    }
    public void setPurConfirm3(String purConfirm3) {
        this.purConfirm3 = purConfirm3;
    }

    @Column(name = "PurConfirm4")
    public String getPurConfirm4() {
        return purConfirm4;
    }
    public void setPurConfirm4(String purConfirm4) {
        this.purConfirm4 = purConfirm4;
    }

    @Column(name = "PurConfirm5")
    public String getPurConfirm5() {
        return purConfirm5;
    }
    public void setPurConfirm5(String purConfirm5) {
        this.purConfirm5 = purConfirm5;
    }

    @Column(name = "PurConfirm6")
    public String getPurConfirm6() {
        return purConfirm6;
    }
    public void setPurConfirm6(String purConfirm6) {
        this.purConfirm6 = purConfirm6;
    }

    @Column(name = "PurConfirm7")
    public String getPurConfirm7() {
        return purConfirm7;
    }
    public void setPurConfirm7(String purConfirm7) {
        this.purConfirm7 = purConfirm7;
    }

    @Column(name = "PurConfirm8")
    public String getPurConfirm8() {
        return purConfirm8;
    }
    public void setPurConfirm8(String purConfirm8) {
        this.purConfirm8 = purConfirm8;
    }

    @Column(name = "PurConfirm9")
    public String getPurConfirm9(){
        return purConfirm9;
    }
    public void setPurConfirm9(String purConfirm9){
        this.purConfirm9 = purConfirm9;
    }

    @Column(name = "Answer1")
    public String getAnswer1() {
        return answer1;
    }
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    @Column(name = "Answer2")
    public String getAnswer2() {
        return answer2;
    }
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    @Column(name = "Answer3")
    public String getAnswer3() {
        return answer3;
    }
    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    @Column(name = "Answer4")
    public String getAnswer4() {
        return answer4;
    }
    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    @Column(name = "Answer5")
    public String getAnswer5() {
        return answer5;
    }
    public void setAnswer5(String answer5) {
        this.answer5 = answer5;
    }

    @Column(name = "Answer6")
    public String getAnswer6() {
        return answer6;
    }
    public void setAnswer6(String answer6) {
        this.answer6 = answer6;
    }

    @Column(name = "Answer7")
    public String getAnswer7() {
        return answer7;
    }
    public void setAnswer7(String answer7) {
        this.answer7 = answer7;
    }

    @Column(name = "Answer8")
    public String getAnswer8() {
        return answer8;
    }
    public void setAnswer8(String answer8) {
        this.answer8 = answer8;
    }

    @Column(name = "Answer9")
    public String getAnswer9() {
        return answer9;
    }
    public void setAnswer9(String answer9) {
        this.answer9 = answer9;
    }

    @Column(name = "Answer10")
    public String getAnswer10() {
        return answer10;
    }
    public void setAnswer10(String answer10) {
        this.answer10 = answer10;
    }

    @Column(name = "Answer11")
    public String getAnswer11() {
        return answer11;
    }
    public void setAnswer11(String answer11) {
        this.answer11 = answer11;
    }

    @Column(name = "Answer12")
    public String getAnswer12(){
        return answer12;
    }
    public void setAnswer12(String answer12){
        this.answer12 = answer12;
    }



}

