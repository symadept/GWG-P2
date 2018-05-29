//
//  CatalogCollectionViewController.m
//  CineScanIOS
//
//  Created by Mustafa Shaik on 5/28/18.
//  Copyright © 2018 Mustafa Shaik. All rights reserved.
//

#import "CatalogCollectionViewController.h"
#import "net/MoviesPresenter.h"
#import "MoviePosterPresenter.h"

@interface CatalogCollectionViewController ()
@property(nonatomic, strong) NSArray *movies;
@end

@implementation CatalogCollectionViewController

static NSString * const reuseIdentifier = @"ImageCell";

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [[MoviesPresenter sharedPresenter] getMostPopularMovies:^(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error) {
        NSDictionary *responseDictionary = [NSJSONSerialization JSONObjectWithData:data options:0 error:&error];
        self.movies = responseDictionary[@"results"];
        dispatch_async(dispatch_get_main_queue(), ^{
            [self.collectionView reloadData];
        });
        NSLog(@"jsonString: %@", responseDictionary);
    }];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

#pragma mark <UICollectionViewDataSource>

- (NSInteger)numberOfSectionsInCollectionView:(UICollectionView *)collectionView {
    return 1;
}


- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section {
    return self.movies.count;
}

- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath {
    UICollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:reuseIdentifier forIndexPath:indexPath];
    
    UIImageView *recipeImageView = (UIImageView *)[cell viewWithTag:100];
    NSString *posterPath = [self.movies objectAtIndex:indexPath.row][@"poster_path"];
    [MoviePosterPresenter fetchPosterForPath:posterPath imageView:recipeImageView];
    
    return cell;
}

#pragma mark <UICollectionViewDelegate>

/*
// Uncomment this method to specify if the specified item should be highlighted during tracking
- (BOOL)collectionView:(UICollectionView *)collectionView shouldHighlightItemAtIndexPath:(NSIndexPath *)indexPath {
	return YES;
}
*/

/*
// Uncomment this method to specify if the specified item should be selected
- (BOOL)collectionView:(UICollectionView *)collectionView shouldSelectItemAtIndexPath:(NSIndexPath *)indexPath {
    return YES;
}
*/

/*
// Uncomment these methods to specify if an action menu should be displayed for the specified item, and react to actions performed on the item
- (BOOL)collectionView:(UICollectionView *)collectionView shouldShowMenuForItemAtIndexPath:(NSIndexPath *)indexPath {
	return NO;
}

- (BOOL)collectionView:(UICollectionView *)collectionView canPerformAction:(SEL)action forItemAtIndexPath:(NSIndexPath *)indexPath withSender:(id)sender {
	return NO;
}

- (void)collectionView:(UICollectionView *)collectionView performAction:(SEL)action forItemAtIndexPath:(NSIndexPath *)indexPath withSender:(id)sender {
	
}
*/

@end
