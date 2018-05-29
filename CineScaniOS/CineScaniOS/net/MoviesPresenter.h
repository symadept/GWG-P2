//
//  MoviesPresenter.h
//  CineScanIOS
//
//  Created by Mustafa Shaik on 5/28/18.
//  Copyright © 2018 Mustafa Shaik. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface MoviesPresenter : NSObject
+ (id)sharedPresenter;

- (void) getMostRatedMovies:(void (^)(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error))completionHandler;
- (void) getMostPopularMovies:(void (^)(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error))completionHandler;
@end
