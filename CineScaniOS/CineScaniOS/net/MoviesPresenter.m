//
//  MoviesPresenter.m
//  CineScanIOS
//
//  Created by Mustafa Shaik on 5/28/18.
//  Copyright © 2018 Mustafa Shaik. All rights reserved.
//

#import "MoviesPresenter.h"
@interface MoviesPresenter()
@end

@implementation MoviesPresenter

+ (id)sharedPresenter {
    static MoviesPresenter *sharedMyManager = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        sharedMyManager = [[self alloc] init];
    });
    return sharedMyManager;
}

- (void) getMostRatedMovies:(void (^)(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error))completionHandler {
    return [self getMoviesForPath:@"https://api.themoviedb.org/3/movie/top_rated?api_key=888eae0cd0bf59076e7d4c7fdbce0d24&language=en-US&page=1" completionHandler:completionHandler];
}

- (void) getMostPopularMovies:(void (^)(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error))completionHandler {
    return [self getMoviesForPath:@"https://api.themoviedb.org/3/movie/popular?api_key=888eae0cd0bf59076e7d4c7fdbce0d24&language=en-US&page=1" completionHandler:completionHandler];
}

- (void) getMoviesForPath:(NSString *)urlString completionHandler:(void (^)(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error))completionHandler {
    NSMutableURLRequest *request = [[NSMutableURLRequest alloc] init];
    [request setHTTPMethod:@"GET"];
    [request setURL:[NSURL URLWithString:urlString]];

    NSURLSessionDataTask *downloadTask = [[NSURLSession sharedSession]
                                          dataTaskWithURL:[NSURL URLWithString:urlString] completionHandler:^(NSData *data, NSURLResponse *response, NSError *error) {
                                              completionHandler(data, response, error);
                                          }];
    
    [downloadTask resume];
}

@end

