package com.example.demo.services;

import com.example.demo.domain.Producer;
import com.example.demo.repositories.ProducerRepository;
import com.example.demo.services.errrors.ObjectNotFound;
import com.example.demo.services.requests.ProducerCreateRequest;
import com.example.demo.services.requests.ProducerEditRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProducerService {

  private ProducerRepository repository;

  public ProducerService(final ProducerRepository repository) {
    this.repository = repository;
  }

  public List<Producer> getAllProducer() {
    return repository.findAll();
  }

  public Producer createProducer(final ProducerCreateRequest request) {
    Producer producer = new Producer();
    producer.setFullname(request.getFullname());
    return repository.save(producer);

  }

  public Producer updateProducer(final ProducerEditRequest request) throws ObjectNotFound {
    Optional<Producer> optionalProducer = repository.findById(request.getId());
    if (optionalProducer.isPresent()) {
      Producer producer = optionalProducer.get();
      producer.setFullname(request.getFullname());
      return repository.save(producer);
    } else {
      throw new ObjectNotFound("Producer not found");
    }


  }

  public Boolean deleteProducer(int id) {
    repository.deleteById(id);
    return true;
  }
}
