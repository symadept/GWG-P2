//
//  MoviePosterPresenter.h
//  CineScanIOS
//
//  Created by Mustafa Shaik on 5/28/18.
//  Copyright © 2018 Mustafa Shaik. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface MoviePosterPresenter : NSObject
+ (void)fetchPosterForPath:(NSString *)path imageView:(UIImageView *)imageView;
@end
