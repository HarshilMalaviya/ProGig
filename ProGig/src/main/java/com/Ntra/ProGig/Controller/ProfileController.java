package com.Ntra.ProGig.Controller;


import com.Ntra.ProGig.Dto.ProfileDto;
import com.Ntra.ProGig.Entity.Profile;
import com.Ntra.ProGig.Service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private  final ProfileService profileService;


    @PostMapping("/addProfile")
    public Profile saveProfile(@RequestBody ProfileDto profile) {
        return profileService.saveProfile(profile);
    }

    @GetMapping("/getProfile")
    public List<ProfileDto> getProfile() {
        return profileService.getProfile();
    }
    @PutMapping("/editProfile")
    public Profile editProfile(@RequestBody ProfileDto profileDto) {
        return profileService.editProfile(profileDto);
    }


}

