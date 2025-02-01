package com.Ntra.ProGig.Service;



import com.Ntra.ProGig.Dto.ProfileDto;
import com.Ntra.ProGig.Entity.Profile;
import com.Ntra.ProGig.Exception.NoContentException;
import com.Ntra.ProGig.Repository.ProfileRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProfileService {
    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Profile saveProfile(ProfileDto profile) {
//        ProfileDto profileDto=this.profileMapper.MapptoProfileDto(profile);
        return profileRepo.save(MapptoProfile(profile));
    }

    public List<ProfileDto> getProfile() {
        List<ProfileDto> profileList= this.profileRepo.findAll().stream().map(this::MapptoProfileDto).toList();
        return profileList;

    }


    public Profile editProfile(ProfileDto profile) {
        Profile exsistingProfile= null;
        try {
            exsistingProfile = profileRepo.findById(profile.getId()).orElseThrow(()->new RuntimeException("NO_SUCH_Profile"));
        } catch (NoContentException e) {
            throw new NoContentException("NO_SUCH_JOB");
        }
        ProfileDto profileDto=this.MapptoProfileDto(exsistingProfile);
//        profileDto.setAddress(profileDto.getAddress());
        profileDto.setZipCode(profile.getZipCode());
        profileDto.setCity(profile.getCity());
        profileDto.setState(profile.getState());
        profileDto.setCountry(profile.getCountry());
        profileDto.setCompanyName(profile.getCompanyName());
        profileDto.setLocation(profile.getLocation());
        profileDto.setExperience(profile.getExperience());
        profileDto.setEducation(profile.getEducation());
        profileDto.setArticles(profile.getArticles());
        profileDto.setCertification(profile.getCertification());
//        profileDto.setReview(profileDto.getReview());
//        profileDto.setPortfolio(profileDto.getPortfolio());
        return profileRepo.save(MapptoProfile(profileDto));

    }


    public ProfileDto MapptoProfileDto(Profile profile) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ProfileDto profileDto = new ProfileDto();
        profileDto = new ModelMapper().map(profile, ProfileDto.class);
        return profileDto;
    }
    public Profile MapptoProfile(ProfileDto profileDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Profile profile = new Profile();
        profile = new ModelMapper().map(profileDto, Profile.class);
        return profile;
    }

}

