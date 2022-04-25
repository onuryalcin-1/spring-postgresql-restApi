package com.onuryalcin.service.impl;

import com.onuryalcin.dto.PersonDto;
import com.onuryalcin.entity.Address;
import com.onuryalcin.entity.Person;
import com.onuryalcin.repo.AddressReposityory;
import com.onuryalcin.repo.PersonRepository;
import com.onuryalcin.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final AddressReposityory addressReposityory;

    @Override
    @Transactional
    public PersonDto save(PersonDto personDto) {
        Assert.notNull(personDto.getName(),"Name must not be empty");

        Person person = new Person();
        person.setName(personDto.getName());
        person.setLastName(personDto.getLastName());
        final Person personDb = personRepository.save(person);

        List<Address> addressList = new ArrayList<>();
        personDto.getPersonAddress().forEach(item -> {
            Address address = new Address();
            address.setAddress(item);
            address.setActive(true);
            address.setAddressType(Address.AddressType.OTHER);
            address.setPerson(personDb);
            addressList.add(address);
        });
        addressReposityory.saveAll(addressList);
        personDto.setId(personDb.getId());
       return personDto;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<PersonDto> getAll() {
        List<Person> people = personRepository.findAll();
        List<PersonDto> personDtos = new ArrayList<>();
        people.forEach(it-> {
            PersonDto personDto2 = new PersonDto();
            personDto2.setId(it.getId());
            personDto2.setName(it.getName());
            personDto2.setLastName(it.getName());
            personDto2.setPersonAddress(it.getPersonAddressList().stream().map(Address::getAddress).collect(Collectors.toList()));
            personDtos.add(personDto2);
        });
        return personDtos;
    }

    @Override
    public Page<PersonDto> getAll(Pageable pageable) {
        return null;
    }
}
