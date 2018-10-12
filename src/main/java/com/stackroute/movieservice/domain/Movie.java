package com.stackroute.movieservice.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document
public class Movie {
    @Id
    String imdbId;

    @NotNull
    @Size(min=2, message="Movie Title should have atleast 2 characters")
    String movieTitle;
    @NotNull
    String posterUrl;
    @NotNull
    double rating;
    @NotNull
    int yearOfRelease;
    @NotNull
    String comments;

    public Movie() {
    }

    public Movie(String imdbId, String movieTitle, String posterUrl, float rating, int yearOfRelease,String comments) {
        this.imdbId = imdbId;
        this.movieTitle = movieTitle;
        this.posterUrl = posterUrl;
        this.rating = rating;
        this.yearOfRelease = yearOfRelease;
        this.comments= comments;
    }
    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "imdbId='" + imdbId + '\'' +
                ", movieTitle='" + movieTitle + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", rating=" + rating +
                ", yearOfRelease=" + yearOfRelease +
                ", comments='" + comments + '\'' +
                '}';
    }
}
