package com.abhi.series.Service;

public interface SeriesDetailsService<T> {

    T getAllEpisodes(String uniqueId);

}
