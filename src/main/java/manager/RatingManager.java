package manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.stereotype.Service;

import model.Rating;

@Service
public class RatingManager {
	private HashMap<String,Rating> ratings;

	public RatingManager() {
		super();
	}

	public RatingManager(HashMap<String,Rating> ratings) {
		super();
		this.ratings = ratings;
	}

	public HashMap<String, Rating> getRatings() {
		return ratings;
	}

	public void setRatings(HashMap<String, Rating> ratings) {
		this.ratings = ratings;
	}
	public ArrayList<Rating> searchRatingById(String id) {
		ArrayList<Rating> list = new ArrayList<Rating>();
		Rating rating = ratings.get(id);
		if (rating != null) {
			list.add(rating);
		}
		return list;
	}
	public ArrayList<Rating> searchRatingByWriter(String writer) {
		Iterator<String> keys = ratings.keySet().iterator();
		ArrayList<Rating> list = new ArrayList<Rating>();
		while (keys.hasNext()) {
			String key = keys.next();
			Rating data = ratings.get(key);
			if (data.getWriter().equals(writer)) {
				list.add(data);
			}
		}
		return list;
	}
	public ArrayList<Rating> searchRatingByHospital(String hospital) {
		Iterator<String> keys = ratings.keySet().iterator();
		ArrayList<Rating> list = new ArrayList<Rating>();
		while (keys.hasNext()) {
			String key = keys.next();
			Rating data = ratings.get(key);
			if (data.getHospital().equals(hospital)) {
				list.add(data);
			}
		}
		return list;
	}
	public byte modifyRating(String id, Rating rating) {
		ArrayList<Rating> list = searchRatingById(id);
		if (list.size() == 0) {
			return 0;
		}
		ratings.put(id, rating);
		return 1;
	}
	public byte addRating(Rating rating) {
		ArrayList<Rating> list = searchRatingById(rating.getId());
		if (list.size() == 0) {
			return 0;
		}
		ratings.put(rating.getId(), rating);
		return 1;
	}
	public byte deleteRating(String id) {
		ArrayList<Rating> list = searchRatingById(id);
		if (list.size() == 0) {
			return 0;
		}
		ratings.remove(id);
		return 1;
	}
}
