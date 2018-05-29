//
//  MoviePosterPresenter.m
//  CineScanIOS
//
//  Created by Mustafa Shaik on 5/28/18.
//  Copyright © 2018 Mustafa Shaik. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "MoviePosterPresenter.h"

@implementation MoviePosterPresenter
+ (void)fetchPosterForPath:(NSString *)path imageView:(UIImageView *)imageView {
    NSURL *url = [NSURL URLWithString:
                  [NSString stringWithFormat:@"https://image.tmdb.org/t/p/w500%@", path]];
    
    NSURLSessionDownloadTask *downloadPhotoTask = [[NSURLSession sharedSession]
                                                   downloadTaskWithURL:url completionHandler:^(NSURL * _Nullable location, NSURLResponse * _Nullable response, NSError * _Nullable error) {
                                                       UIImage *downloadedImage = [UIImage imageWithData:
                                                                                   [NSData dataWithContentsOfURL:location]];
                                                       dispatch_async(dispatch_get_main_queue(), ^{
                                                           imageView.image = downloadedImage;
                                                       });
                                                   }];
    
    [downloadPhotoTask resume];
}
@end
