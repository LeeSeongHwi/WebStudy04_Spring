package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.io.UncheckedIOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.LoginGroup;
import kr.or.ddit.validate.constraints.TelNumber;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 회원 관리용 Domain Layer
 * 
 */
@Data
@EqualsAndHashCode(of = "memId")
@ToString
@NoArgsConstructor
public class MemberVO implements Serializable{
   //transient : 직렬화에서 배제
   //@JsonIgnore : 마셜링에서 배제
   //@ToStringExclude : 객체에서 제외

   
   public MemberVO(String memId, String memPass) {
      super();
      this.memId = memId;
      this.memPass = memPass;
   }
   
   private int rnum;
   @NotBlank(groups = {DeleteGroup.class, InsertGroup.class, LoginGroup.class}) //javax로 하기 //기본, delete 이면 검증을 해라
   private String memId;
   @NotBlank(groups = {LoginGroup.class, Default.class})
   @Size(min = 4, max = 12, groups = {LoginGroup.class, Default.class})
   private String memPass;   
   @NotBlank(groups = InsertGroup.class) //가입(insert)할떄는 검증을 하고 수정(update or default)할떄는 검증을 하지 않음.
   private String memName;
   @Size(min = 6,max = 6)
   @JsonIgnore
   @ToStringExclude
   private transient String memRegno1;
   @JsonIgnore
   @ToStringExclude
   private transient String memRegno2;
//   @JsonFormat(shape = Shape.STRING) // json 보낼떄 동작함.
   @DateTimeFormat(iso = ISO.DATE_TIME)
   private LocalDateTime memBir;
   @NotBlank
   private String memZip;
   @NotBlank
   private String memAdd1;
   @NotBlank
   private String memAdd2;
   @TelNumber(regex = "\\d{2,3}\\)\\d{3,4}-\\d{4}")
   private String memHometel;
   @TelNumber  //@Pattern >> 사용시 notBlank 역할도 하기떄문에 문제가 있음.
   private String memComtel;
   @NotBlank
   @TelNumber
   private String memHp;
   @Email
   private String memMail;
   private String memJob;
   private String memLike;
   private String memMemorial;
//   @JsonFormat(shape = Shape.STRING) // json 보낼떄 동작함.
   @DateTimeFormat(iso = ISO.DATE) // json받을떄 동작함.
   private LocalDate memMemorialday;
   private Integer memMileage;
   private boolean memDelete;
   
   //has Many 관계 1:N
   @JsonIgnore //마샬링 제외 // 아래꺼는 직렬화 제외
   private transient List<CartVO> cartList;
   // MEMBER(1) : CART[PROD(1)](N) // 관계앤터티가 없으면 다대다 관계 안됌
   
   private String memRole;
   
   private byte[] memImg; //데이터베이스 연결용
   private MultipartFile memImage; //클라이언트의 업로드 파일 수신용
   public void setMemImage(MultipartFile memImage) {
      if(memImage == null || memImage.isEmpty()) return;
      this.memImage = memImage;
      try {
         this.memImg = memImage.getBytes();
      } catch (IOException e) {
         throw new UncheckedIOException(e);
      }
   }
   public String getBase64Img() {
      if(memImg == null) return null;
      else return Base64.getEncoder().encodeToString(memImg);
   }
   
   
   
}