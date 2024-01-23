package kr.or.ddit.prod.service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.exception.PKNotFoundException;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

/**
 * AOP 방법론
 * 상품 등록과 상품 수정시 트랜잭션 관리기능이 중복되는 문제.
 * Core : 상품 등록과 상품 수정
 * Cross-cutting : 트랜잭션 관리기능
 * Target : createprod, modifyprod, retrieveProd, retrieveprodList
 * Advice : 트랜잭션 관리 advice 구현 or advisor
 * +
 * Pointcut 작성 (createprod, modifyprod)
 * ==> aspect
 * 
 * 모든 로직의 실행 소요시간을 시스템 로그로 기록하라.
 * Core : 모든 로직 - MemberService, ProdService, BuyerService...
 * CC : 실행 소요시간을 시스템 로그로 기록
 */
@Service
@RequiredArgsConstructor
public class ProdServiceImpl implements ProdService {
	
	private final ProdDAO dao;
	
	@Value("#{appInfo.prodPath}")
	private Resource prodFolder;
	
	/**
	 * 상품 등록이나 수정시 업로드된 상품 이미지 2진 데이터 저장
	 */
	private void processProdImage(ProdVO prod) {
		MultipartFile prodImage = prod.getProdImage();
		if(prodImage==null) return;
//		if(1==1) throw new RuntimeException("강제 발생 예외");
		try {
			Resource imageFile = prodFolder.createRelative(prod.getProdImg());
			prodImage.transferTo(imageFile.getFile());
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	@Override
	public ServiceResult createProd(ProdVO prod) {
		ServiceResult result = dao.insertProd(prod) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		if(result == ServiceResult.OK)
			processProdImage(prod);
		return result;
	}

	@Override
	public List<ProdVO> retrieveProdList(PaginationInfo paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		return dao.selectProdList(paging);
	}

	@Override
	public ProdVO retrieveProd(String prodId) {
		ProdVO prod = dao.selectProd(prodId);
		if(prod==null)
			throw new PKNotFoundException(String.format("%s 에 해당하는 상품 없음.", prodId));
		return prod;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		processProdImage(prod);
		ServiceResult result = dao.updateProd(prod) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		if(result == ServiceResult.OK)
			processProdImage(prod);
		return result;
	}

}











