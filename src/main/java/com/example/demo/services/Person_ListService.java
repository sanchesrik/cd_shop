package com.example.demo.services;

import com.example.demo.domain.Disc;
import com.example.demo.domain.Person_List;
import com.example.demo.domain.Staff;
import com.example.demo.domain.Users;
import com.example.demo.repositories.DiscRepository;
import com.example.demo.repositories.ListRepository;
import com.example.demo.repositories.StaffRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.errrors.ObjectNotFound;
import com.example.demo.services.requests.ListCreateRequest;
import com.example.demo.services.requests.ListEditRequest;
import javassist.NotFoundException;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Person_ListService {
    private final ListRepository listRepository;
    private final DiscRepository discRepository;
    private final UserRepository userRepository;
    private final StaffRepository staffRepository;

    public Person_ListService(ListRepository listRepository, DiscRepository discRepository,
                              UserRepository userRepository, StaffRepository staffRepository) {
        this.listRepository = listRepository;
        this.discRepository = discRepository;
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
    }

    public List<Person_List> getAll(){
        return listRepository.findAll();
    }

    public Person_List findOne(Integer id) throws ObjectNotFound {
        Optional<Person_List> optionalList = listRepository.findById(id);
        if (optionalList.isPresent())
            return optionalList.get();
        throw new ObjectNotFound("List not founded");
    }
    public Person_List createList(final ListCreateRequest request) throws ObjectNotFound {
        Optional<Disc> optionalDisc = discRepository.findById(request.getDisc_id()) ;
        if (optionalDisc.isEmpty())
            throw new ObjectNotFound("Disc is not found");

        Optional<Staff> optionalStaff =staffRepository.findById(request.getStaff_id());
        if (optionalStaff.isEmpty())
            throw new ObjectNotFound("Staff is not found");

        Optional<Users> optionalUser = userRepository.findById(request.getUser_id());
        if (optionalUser.isEmpty())
            throw new ObjectNotFound("User is not found");

        Person_List list = new Person_List();
        list.setAction(request.getAction());
        list.setDisc(optionalDisc.get());
        list.setUser(optionalUser.get());
        list.setStaff(optionalStaff.get());
        list.setWas_created(request.getWas_created());
        return listRepository.save(list);
    }

    public Person_List updateList(final ListEditRequest request) throws ObjectNotFound {

        Optional<Person_List> optionalList = listRepository.findById(request.getId());
        if (optionalList.isEmpty())
            throw new ObjectNotFound("List is not found");

        Optional<Disc> optionalDisc = discRepository.findById(request.getDisc_id()) ;
        if (optionalDisc.isEmpty())
            throw new ObjectNotFound("Disc is not found");

        Optional<Staff> optionalStaff = staffRepository.findById(request.getStaff_id());
        if (optionalStaff.isEmpty())
            throw new ObjectNotFound("Staff is not found");

        Optional<Users> optionalUser = userRepository.findById(request.getUser_id());
        if (optionalUser.isEmpty())
            throw new ObjectNotFound("User is not found");

        Person_List list = new Person_List();
        list.setId(request.getId());
        list.setAction(request.getAction());
        list.setDisc(optionalDisc.get());
        list.setUser(optionalUser.get());
        list.setStaff(optionalStaff.get());
        list.setWas_created(request.getWas_created());
        return listRepository.save(list);
    }
    public Boolean deleteList(int id){
        listRepository.deleteById(id);
        return true;
    }
}
