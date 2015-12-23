package ch.smartlinksa.intern.dao.entity;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

@Service
public class AuditorAwareImpl implements AuditorAware<String>{
    @Override
    public String getCurrentAuditor() {
        return "Lien_default";
    }
}
