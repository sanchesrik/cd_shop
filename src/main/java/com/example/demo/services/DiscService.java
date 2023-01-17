package com.example.demo.services;

import com.example.demo.domain.Disc;
import com.example.demo.domain.Genre;
import com.example.demo.domain.Producer;
import com.example.demo.repositories.DiscRepository;
import com.example.demo.repositories.GenreRepository;
import com.example.demo.repositories.ProducerRepository;
import com.example.demo.services.errrors.ObjectNotFound;
import com.example.demo.services.requests.DiscCreateRequest;
import com.example.demo.services.requests.DiscEditRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiscService {
  private final DiscRepository discRepository;
  private final GenreRepository genreRepository;
  private final ProducerRepository producerRepository;

  public DiscService(final DiscRepository discRepository, final GenreRepository genreRepository, final ProducerRepository producerRepository) {
    this.discRepository = discRepository;
    this.genreRepository = genreRepository;
    this.producerRepository = producerRepository;
  }

  @Transactional
  public List<Disc> getAllDisc() {
    return discRepository.findAll();
  }

  public Disc createNewDisc(final DiscCreateRequest request) throws ObjectNotFound {
    List<Producer> producers = producerRepository.findById(request.getProducer_id());
    Optional<Genre> optionalGenre = genreRepository.findById(request.getGenre_id());
    if (optionalGenre.isPresent()) {
      Genre genre = optionalGenre.get();
      Disc disc = new Disc();
      disc.setName(request.getName());
      disc.setGenre(genre);
      disc.setYear(request.getYear());
      disc.setCountry(disc.getCountry());
      disc.setProducer(new Producer());
      return discRepository.save(disc);
    } else {
      throw new ObjectNotFound("Genre not found");
    }
  }

  public Disc updateDiscInfo(final DiscEditRequest request) throws ObjectNotFound {
    Optional<Disc> optionalDisc = discRepository.findById(request.getId());
    if (optionalDisc.isPresent()) {
      Disc disc = optionalDisc.get();
      Optional<Producer> producer = producerRepository.findById(request.getProducer_id());
      if (!producer.isPresent()) {
        throw new ObjectNotFound("Producer not found");
      }
      Optional<Genre> optionalGenre = genreRepository.findById(request.getGenre_id());
      if (optionalGenre.isPresent()) {
        Genre genre = optionalGenre.get();
        disc.setName(request.getName());
        disc.setYear(request.getYear());
        disc.setGenre(genre);
        disc.setCountry(request.getCountry());
        disc.setProducer(new Producer());
        return discRepository.save(new Disc());
      } else {
        throw new ObjectNotFound("Genre not found");
      }
    } else {
      throw new ObjectNotFound("Disc not found");
    }
  }

  public boolean DeleteDisc(int id) {
    discRepository.deleteById(id);
    return true;
  }
}
