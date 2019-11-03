package manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DB.RatingDAO;
import model.Rating;

@Service
public class RatingManager {
	// @key : Rating.id
	private HashMap<String,Rating> ratings;
	@Autowired
	private RatingDAO ratingDAO;

	public RatingManager() {
		super();
		ratings = ratingDAO.load();
	}

	public RatingManager(HashMap<String,Rating> ratings, String lastId) {
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
	public ArrayList<Rating> search(String writer, String hospital) {
		Iterator<String> keys = ratings.keySet().iterator();
		ArrayList<Rating> list = new ArrayList<Rating>();
		while (keys.hasNext()) {
			String key = keys.next();
			Rating data = ratings.get(key);
			if (data.getHospital().equals(hospital) && data.getWriter().equals(writer)) {
				list.add(data);
			}
		}
		return list;
	}
	public double getHospitalRating(String hospital) {
		ArrayList<Rating> list = searchRatingByHospital(hospital);
		int size = list.size();
		if (size == 0) {
			return 0;
		}
		int sum = 0;
		for (Rating rating : list) {
			sum += rating.getScale();
		}
		return (double)sum / size;
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
	public byte addRating(String writer, String hospital, int scale, String content) {
		ArrayList<Rating> list = search(writer, hospital);
		if (list.size() == 0) {
			String id = writer + "#" + hospital;
			Rating rating = new Rating(id, writer, hospital, scale, content);
			ratings.put(id, rating);
		}
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
