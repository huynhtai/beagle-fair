package ch.smartlinksa.intern.business.service.impl;

import ch.smartlinksa.intern.business.constant.TransactionConstant;
import ch.smartlinksa.intern.business.service.IRefundService;
import ch.smartlinksa.intern.business.util.SessionUtil;
import ch.smartlinksa.intern.dao.entity.RefundTransaction;
import ch.smartlinksa.intern.dao.repository.RefundRepository;
import ch.smartlinksa.intern.interfaces.request.RefundResquest;
import ch.smartlinksa.intern.interfaces.response.RefundResponse;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefundServiceImpl implements IRefundService {

    @Autowired
    private RefundRepository refundRepository;

    public RestApiResponse<RefundResponse> addRefundProduct(RefundResquest refundResquest) {
        RefundTransaction refundTransactionEntity = covertRefundTransactionToEntity(refundResquest);
        refundTransactionEntity = refundRepository.save(refundTransactionEntity);
        return sendResponseWhenAddUserSuccessfully(refundTransactionEntity, refundResquest);
    }

    private RestApiResponse<RefundResponse> sendResponseWhenAddUserSuccessfully(RefundTransaction refundTransactionEntity, RefundResquest refundResquest) {
        RestApiResponse<RefundResponse> refundProductResponse = new RestApiResponse<RefundResponse>();
        refundProductResponse.setBody(convertRefundProductEntityToRefundProductResponse(refundTransactionEntity, refundResquest));
        return refundProductResponse;
    }

    private RefundResponse convertRefundProductEntityToRefundProductResponse(RefundTransaction refundTransactionEntity, RefundResquest refundResquest) {
        RefundResponse refundResponse = new RefundResponse();

        refundResponse.setId(refundTransactionEntity.getId());
        refundResponse.setProductCode(refundTransactionEntity.getProductCode());
        refundResponse.setAddress(refundTransactionEntity.getAddress());
        refundResponse.setReason(refundTransactionEntity.getReason());
        refundResponse.setTotalPrice(refundTransactionEntity.getTotalPrice());
        refundResponse.setResultCode(refundTransactionEntity.getResultCode());
        refundResponse.setResultMessage(refundTransactionEntity.getResultMessage());

        return  refundResponse;
    }

    private RefundTransaction covertRefundTransactionToEntity(RefundResquest refundResquest) {
        RefundTransaction refundTransactionEntity = new RefundTransaction();

        refundTransactionEntity.setUser(SessionUtil.getCurrentUser());
        refundTransactionEntity.setAddress(refundResquest.getAddress());
        refundTransactionEntity.setReason(refundResquest.getReason());
        refundTransactionEntity.setResultCode(TransactionConstant.RESULT_CODE);
        refundTransactionEntity.setResultMessage(TransactionConstant.RESULT_MESSAGE);

        return refundTransactionEntity;
    }
}
