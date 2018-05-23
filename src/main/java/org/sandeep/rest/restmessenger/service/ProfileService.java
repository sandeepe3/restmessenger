package org.sandeep.rest.restmessenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sandeep.rest.restmessenger.database.DatabaseClass;
import org.sandeep.rest.restmessenger.model.Profile;

public class ProfileService {
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();

	public ProfileService() {
		System.out.println("Inside ProfileService constructor...");
		profiles.put("Sandeep", new Profile(1L, "Sandeep", "Erukulla", "CA"));
	}

	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}

	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile updateProfile(Profile profile) {
		if(profile.getId() <= 0)
			return null;
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
}