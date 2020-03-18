package cn.gpnu.gmall.beans;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

public class UmsMemberLevel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private String id;
    @Column
    private String name;
    @Column
    private int growthPoint;
    @Column
    private int defaultStatus;
    @Column
    private BigDecimal freeFreightPoint;
    @Column
    private int commentGrowthPoint;
    @Column
    private int priviledgeFreeFreight;
    @Column
    private int priviledgeSignIn;
    @Column
    private int priviledgeComment;
    @Column
    private int priviledgePromotion;
    @Column
    private int priviledgeMember_price;
    @Column
    private int priviledgeBirthday;
    @Column
    private String note;

    public UmsMemberLevel() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getGrowthPoint() {
        return growthPoint;
    }
    public void setGrowthPoint(int growthPoint) {
        this.growthPoint = growthPoint;
    }
    public int getDefaultStatus() {
        return defaultStatus;
    }
    public void setDefaultStatus(int defaultStatus) {
        this.defaultStatus = defaultStatus;
    }
    public BigDecimal getFreeFreightPoint() {
        return freeFreightPoint;
    }
    public void setFreeFreightPoint(BigDecimal freeFreightPoint) {
        this.freeFreightPoint = freeFreightPoint;
    }
    public int getCommentGrowthPoint() {
        return commentGrowthPoint;
    }
    public void setCommentGrowthPoint(int commentGrowthPoint) {
        this.commentGrowthPoint = commentGrowthPoint;
    }
    public int getPriviledgeFreeFreight() {
        return priviledgeFreeFreight;
    }
    public void setPriviledgeFreeFreight(int priviledgeFreeFreight) {
        this.priviledgeFreeFreight = priviledgeFreeFreight;
    }
    public int getPriviledgeSignIn() {
        return priviledgeSignIn;
    }
    public void setPriviledgeSignIn(int priviledgeSignIn) {
        this.priviledgeSignIn = priviledgeSignIn;
    }
    public int getPriviledgeComment() {
        return priviledgeComment;
    }
    public void setPriviledgeComment(int priviledgeComment) {
        this.priviledgeComment = priviledgeComment;
    }
    public int getPriviledgePromotion() {
        return priviledgePromotion;
    }
    public void setPriviledgePromotion(int priviledgePromotion) {
        this.priviledgePromotion = priviledgePromotion;
    }
    public int getPriviledgeMember_price() {
        return priviledgeMember_price;
    }
    public void setPriviledgeMember_price(int priviledgeMember_price) {
        this.priviledgeMember_price = priviledgeMember_price;
    }
    public int getPriviledgeBirthday() {
        return priviledgeBirthday;
    }
    public void setPriviledgeBirthday(int priviledgeBirthday) {
        this.priviledgeBirthday = priviledgeBirthday;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "UmsMemberLevel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", growthPoint=" + growthPoint +
                ", defaultStatus=" + defaultStatus +
                ", freeFreightPoint=" + freeFreightPoint +
                ", commentGrowthPoint=" + commentGrowthPoint +
                ", priviledgeFreeFreight=" + priviledgeFreeFreight +
                ", priviledgeSignIn=" + priviledgeSignIn +
                ", priviledgeComment=" + priviledgeComment +
                ", priviledgePromotion=" + priviledgePromotion +
                ", priviledgeMember_price=" + priviledgeMember_price +
                ", priviledgeBirthday=" + priviledgeBirthday +
                ", note='" + note + '\'' +
                '}';
    }

}
