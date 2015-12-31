package ch.smartlinksa.intern.business.service;

import ch.smartlinksa.intern.interfaces.request.RefundResquest;
import ch.smartlinksa.intern.interfaces.response.RefundResponse;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;

public interface IRefundService {

    RestApiResponse<RefundResponse> addRefundProduct(RefundResquest refundResquest);
}
