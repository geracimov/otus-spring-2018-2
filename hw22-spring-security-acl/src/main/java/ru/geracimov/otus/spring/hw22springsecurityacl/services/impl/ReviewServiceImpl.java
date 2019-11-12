package ru.geracimov.otus.spring.hw22springsecurityacl.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geracimov.otus.spring.hw22springsecurityacl.config.AclCreationService;
import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Review;
import ru.geracimov.otus.spring.hw22springsecurityacl.exception.NotFoundException;
import ru.geracimov.otus.spring.hw22springsecurityacl.repository.ReviewRepository;
import ru.geracimov.otus.spring.hw22springsecurityacl.services.ReviewService;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final AclCreationService aclCreationService;

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                               .orElseThrow(NotFoundException::new);
    }

    public Iterable<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public boolean delete(Long id) {
        Review review = getReviewById(id);
        return delete(review);
    }

    public boolean delete(Review review) {
        try {
            reviewRepository.delete(review);
            aclCreationService.dropAcl(new ObjectIdentityImpl(review));
            return true;
        } catch (Exception var3) {
            log.error("Error deleting review - " + review.getId(), var3);
            return false;
        }
    }

    public void save(Review review) {
        reviewRepository.save(review);
        aclCreationService.addDefaultPrivilege(new ObjectIdentityImpl(review));
    }
}
