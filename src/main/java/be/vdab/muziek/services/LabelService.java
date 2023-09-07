package be.vdab.muziek.services;

import be.vdab.muziek.domain.Label;
import be.vdab.muziek.repositories.LabelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class LabelService {
    private final LabelRepository labelRepository;

    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }
    public Optional<Label> findById(long id){
        return labelRepository.findById(id);
    }
}
