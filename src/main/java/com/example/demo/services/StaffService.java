package com.example.demo.services;

import com.example.demo.domain.Position;
import com.example.demo.domain.Staff;
import com.example.demo.repositories.PositionRepository;
import com.example.demo.repositories.StaffRepository;
import com.example.demo.services.errrors.ObjectNotFound;
import com.example.demo.services.requests.StaffCreateRequest;
import com.example.demo.services.requests.StaffEditRequest;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    private final StaffRepository staffRepository;
    private final PositionRepository positionRepository;

    public StaffService (final StaffRepository staffRepository, final PositionRepository positionRepository){
        this.staffRepository=staffRepository;
        this.positionRepository=positionRepository;
    }

    public List<Staff> getAllStaff(){
        return staffRepository.findAll();
    }

    public Staff createEmployee(StaffCreateRequest request) throws ObjectNotFound{
        Staff staff = new Staff();
        staff.setFirstname(request.getFirstname());
        staff.setLastname(request.getLastname());
        Optional<Position> position = positionRepository.findById(request.getPosition_id());
        if (position.isPresent()) {
            staff.setPosition(position.get());
            return staffRepository.save(staff);
        }
        else
            throw new ObjectNotFound("Staff not found");
    }

    public Staff updateStaff(final StaffEditRequest request) throws ObjectNotFound{
        Optional<Staff> optionalStaff = staffRepository.findById(request.getId());
        if (optionalStaff.isPresent()) {
            Staff staff = optionalStaff.get();
            staff.setFirstname(request.getFirstname());
            staff.setLastname(request.getLastname());

            Optional<Position> position = positionRepository.findById(request.getPosition_id());
            if (position.isPresent()) {
                staff.setPosition(position.get());
                return staffRepository.save(staff);
            }
            else
                throw new ObjectNotFound("Staff not found");
        } else
            throw new ObjectNotFound("User not found");
    }
    public Boolean deleteStaff(Integer id) {
        staffRepository.deleteById(id);
        return true;
    }
}
