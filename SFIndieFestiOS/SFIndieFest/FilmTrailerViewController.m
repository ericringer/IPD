//
//  FilmTrailerViewController.m
//  SFIndieFest
//
//  Created by eric ringer on 9/22/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "FilmTrailerViewController.h"

@interface FilmTrailerViewController ()

@end

@implementation FilmTrailerViewController
@synthesize film,trailerWebView;
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    [self.navigationController.navigationBar setTitleTextAttributes:
     [NSDictionary dictionaryWithObjectsAndKeys:
      [UIFont fontWithName:@"Superclarendon-Bold " size:17],
      NSFontAttributeName, nil]];
    
    NSURLRequest *request = [[NSURLRequest alloc] initWithURL: [NSURL URLWithString: [film trailerUrl]] cachePolicy: NSURLRequestUseProtocolCachePolicy timeoutInterval: 10];
    [self.trailerWebView loadRequest: request];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
