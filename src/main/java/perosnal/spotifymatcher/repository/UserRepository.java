package perosnal.spotifymatcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import perosnal.spotifymatcher.model.User;

import java.lang.annotation.Native;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


    @Query("select t from User u JOIN u.tracks t "
            + "WHERE u.id =?1 ")
    List<String> findTracksById(String id);

//    @Query("select u from User u JOIN u.tracks t "
//            + "WHERE t= ?1")
//    List<User> findUsersBySharedTracks(String track);

    List<User> findByCountry(String country);

    User findByTokenAccessToken(String accessToken);


    @Query(value ="SELECT * from user where id IN ("+
            "SELECT that.user_id " +
            "FROM user_top_tracks AS this INNER JOIN user_top_tracks " +
            "AS that ON that.user_id <> this.user_id " +
            "AND that.track_id = this.track_id WHERE this.user_id = ?1 " +
            "GROUP BY that.user_id HAVING COUNT(*) >= ?2 )", nativeQuery = true)
    List<User> getMatches(String userId, int matchingTracks);


}
