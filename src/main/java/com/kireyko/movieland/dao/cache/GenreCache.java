package com.kireyko.movieland.dao.cache;

import com.kireyko.movieland.dao.GenreDao;
import com.kireyko.movieland.dao.jdbc.GenreDaoImpl;
import com.kireyko.movieland.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class GenreCache implements GenreDao{
    private static final Logger LOG = LoggerFactory.getLogger(GenreDaoImpl.class);
    private volatile List <Genre> genreListCached;

    @Autowired
    private GenreDao genreDao;

    @Override
    public List<Genre> getAll() {
        LOG.info("Genre cache. Start query to get list of genres from cache");
        long startTime = System.currentTimeMillis();
        List <Genre> genreListCopy = new ArrayList<>(genreListCached);
        LOG.info("Genre cache. Finish query to get genres list from cache. It took {} ms",  System.currentTimeMillis() - startTime);
        return genreListCopy;
    }

    @Scheduled(fixedRateString = "${genreCache.fixedRate.in.milliseconds}", initialDelayString = "${genreCache.fixedRate.in.milliseconds}")
    @PostConstruct
    private void invalidateCache(){
        LOG.info("Genre cache. Start query to get list of genres from DB");
        long startTime = System.currentTimeMillis();
        genreListCached = genreDao.getAll();
        LOG.info("Genre cache. Finish query to get genres list from DB. It took {} ms",  System.currentTimeMillis() - startTime);
      }
}
