package com.mydating.dating.service;

import java.util.Comparator;

import com.mydating.dating.dto.Matchinguser;

public class UserSorting implements Comparator<Matchinguser> {

	@Override
	public int compare(Matchinguser o1, Matchinguser o2) {
		if(o1.getAgeDiff()<o2.getAgeDiff()) {
			return -1;
		}
		else if(o1.getAgeDiff()>o2.getAgeDiff()) {
			return 1;
		}else if(o1.getAgeDiff()==o2.getAgeDiff()) {
			if(o1.getMic()<o2.getMic()) {
				return 1;
			}
			else if(o1.getMic()>o2.getMic()) {
				return -1;
			}
		}
		return 0;
	}

}
