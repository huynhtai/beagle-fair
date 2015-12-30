package ch.smartlinksa.intern.business.service;

import ch.smartlinksa.intern.interfaces.request.SellRequest;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import ch.smartlinksa.intern.interfaces.response.SellRespone;

public interface ISellService {
    public RestApiResponse<SellRespone> sellProduct(SellRequest sellRequest);
}
