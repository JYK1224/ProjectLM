package lm.model;

public class DisposeVo {

   // Fields
      private String   disdate;        // 폐기일자
      private String   aname;          // 상품분류
      private String   pid;            // 상품코드
      private String   pname;          // 상품명
      private String   disprice;       // 폐기가
      private String   innum;          // 현재재고
      private String   stocknum;       // 주문일
      private String   disnum;         // 폐기수량
      private String   userid;         // 출고직원
      private String   dname;          // 거래처명
      private String   iprice;         // 입고가
      
      // Constructor
      public DisposeVo() {}

      // getter/setter
      public String getDisdate() {
         return disdate;
      }

      public void setDisdate(String disdate) {
         this.disdate = disdate;
      }

      public String getAname() {
         return aname;
      }

      public void setAname(String aname) {
         this.aname = aname;
      }

      public String getPid() {
         return pid;
      }

      public void setPid(String pid) {
         this.pid = pid;
      }

      public String getPname() {
         return pname;
      }

      public void setPname(String pname) {
         this.pname = pname;
      }

      public String getDisprice() {
         return disprice;
      }

      public void setDisprice(String disprice) {
         this.disprice = disprice;
      }

      public String getInnum() {
         return innum;
      }

      public void setInnum(String innum) {
         this.innum = innum;
      }

      public String getStocknum() {
         return stocknum;
      }

      public void setStocknum(String stocknum) {
         this.stocknum = stocknum;
      }

      public String getDisnum() {
         return disnum;
      }

      public void setDisnum(String disnum) {
         this.disnum = disnum;
      }

      public String getUserid() {
         return userid;
      }

      public void setUserid(String userid) {
         this.userid = userid;
      }

      public String getDname() {
         return dname;
      }

      public void setDname(String dname) {
         this.dname = dname;
      }

      public String getIprice() {
         return iprice;
      }

      public void setIprice(String iprice) {
         this.iprice = iprice;
      }

      //toString
      @Override
      public String toString() {
         return "DisVo [disdate=" + disdate + ", aname=" + aname + ", pid=" + pid + ", pname=" + pname
               + ", disprice=" + disprice + ", innum=" + innum + ", stocknum=" + stocknum + ", disnum=" + disnum
               + ", userid=" + userid + ", dname=" + dname + ", iprice=" + iprice + "]";
      }
      

      
}