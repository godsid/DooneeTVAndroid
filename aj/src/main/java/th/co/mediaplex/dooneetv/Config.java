package th.co.mediaplex.dooneetv;

/**
 * Created by Banpot.S on 10/16/14 AD.
 */
public class Config {
    final static public String urlApiInit = "https://api.doonee.tv/api/init";
    final static public String urlApiHome = "https://api.doonee.tv/api/home";
    final static public String urlApiMemberLogin = "https://api.doonee.tv/api/member/login";
    final static public String urlApiMemberRegister = "https://api.doonee.tv/api/member/register";
    final static public String urlApiMemberHistory = "https://api.doonee.tv/api/member/history?member_id={member_id}&page={page}&limit={limit}";
    final static public String urlApiMemberFavorite = "https://api.doonee.tv/api/member/favorite?member_id={member_id}&page={page}&limit={limit}";
    final static public String urlApiMovie = "https://api.doonee.tv/api/movie/{movie_id}";
    final static public String urlApiMovieHot = "https://api.doonee.tv/api/movie/hot?page={page}&limit={limit}";
    final static public String urlApiMovieSearch = "https://api.doonee.tv/api/movie/search?q={q}&page={page}&limit={limit}";
    final static public String urlApiMovieSuggestion = "https://api.doonee.tv/api/movie/suggestion?q={q}&page={page}&limit={limit}";
    final static public String urlApiMovieCategory = "https://api.doonee.tv/api/movie/category/{category_id}?page={page}&limit={limit}";

    final static public String videoUrl = "http://122.155.197.142:1935/vod/mp4:{movie_path}/playlist.m3u8";
    final static public String sampleVideoHttp = "http://122.155.197.142:1935/vod/mp4:sample.mp4/playlist.m3u8";
    final static public String sampleVideoRTSP = "rtsp://122.155.197.142:1935/vod/sample.mp4";
    final static public String sampleVideoWeb = "http://122.155.197.142/sample.mp4";



}
