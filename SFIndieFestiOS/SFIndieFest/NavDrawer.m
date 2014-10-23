//
//  NavDrawer.m
//  SFIndieFest
//
//  Created by eric ringer on 9/23/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "NavDrawer.h"
@implementation NavDrawer
@synthesize parentView,navDrawerWidth, navDrawerX, openDrawer, closeDrawer, menuItems,navDrawer;


//Open or close drawer on swipe gesture
-(void)handleSwipes:(UISwipeGestureRecognizer *)sender{
    [self swingDrawer];
}

//Open or close drawer based on x position
-(void)swingDrawer{
    
    [UIView beginAnimations:nil context:nil];
    [UIView setAnimationDelegate:self];
    [UIView setAnimationDuration:-5];
    
    CGFloat new_x = 0;
    if(navDrawer.frame.origin.x < parentView.view.frame.origin.x){
        
        new_x = navDrawer.frame.origin.x + navDrawerWidth;
    }else{
        new_x = navDrawer.frame.origin.x - navDrawerWidth;
    }
    navDrawer.frame = CGRectMake(new_x, navDrawer.frame.origin.y, navDrawer.frame.size.width, navDrawer.frame.size.height);
    
    [UIView commitAnimations];
}

//Creates nav drawer
-(void)createDrawer
{
    //Prepare drawer
    int statusBarHeight = [UIApplication sharedApplication].statusBarFrame.size.height;
    int navBarHeight = parentView.navigationController.navigationBar.frame.size.height + statusBarHeight;
    navDrawerWidth = parentView.view.frame.size.width * 0.75;
    
    navDrawerX = parentView.view.frame.origin.x - navDrawerWidth;
    navDrawer = [[UIView alloc]initWithFrame:CGRectMake(navDrawerX, parentView.view.frame.origin.y + navBarHeight, navDrawerWidth, parentView.view.frame.size.height - navBarHeight)];
    navDrawer.backgroundColor = [UIColor lightGrayColor];
    
    closeDrawer = [[UISwipeGestureRecognizer alloc]initWithTarget:self action:@selector(handleSwipes:)];
    openDrawer = [[UISwipeGestureRecognizer alloc]initWithTarget:self action:@selector(handleSwipes:)];
    closeDrawer.direction = UISwipeGestureRecognizerDirectionLeft;
    openDrawer.direction = UISwipeGestureRecognizerDirectionRight;
    
    [parentView.view addGestureRecognizer:openDrawer];
    [parentView.view addGestureRecognizer:closeDrawer];
    
    //Scroll view for nav items
    UIScrollView *theScrollView = [[UIScrollView alloc]initWithFrame:CGRectMake(3.0f, 10.0f, navDrawerWidth, navDrawer.frame.size.height)];
    [theScrollView setScrollEnabled:YES];
    [theScrollView setShowsHorizontalScrollIndicator:NO];
    [theScrollView setShowsVerticalScrollIndicator:YES];
    [theScrollView setBackgroundColor:[UIColor clearColor]];
    [theScrollView setIndicatorStyle:UIScrollViewIndicatorStyleDefault];
    [theScrollView setCanCancelContentTouches:NO];
    [theScrollView setClipsToBounds:YES];
    
    //Dimension of nav items
    float orderOfButtons = 10.0f;
    float buttonWidth = 227.0f;
    float buttonHeight = 50.0f;
    int buttonSeparator = 10;
    
    UIImage *logo = [UIImage imageNamed:@"sfindieicon.png"];
    
    UIImageView *logoImageView = [[UIImageView alloc] initWithFrame:CGRectMake((buttonWidth - logo.size.width), orderOfButtons, logo.size.width, buttonHeight)];
    
    [logoImageView setImage:logo];
    
    [theScrollView addSubview:logoImageView];
    
    orderOfButtons += (buttonHeight + buttonSeparator);

    //Array of nav item titles
    menuItems = [[NSArray alloc]initWithObjects:@"Home", @"Films", @"Schedule", @"Search", @"Comment Feed", @"About SF IndieFest", nil];
    
    //Loop through menuItems array and add nav item
    for(int b=0; b<[menuItems count]; b++){
    UIImageView *image = [[UIImageView alloc] initWithFrame:CGRectMake(3.0f, orderOfButtons + ((    buttonHeight - 30)/ 2), 30, 30)];
        [image setImage:[self getNavImage:b]];
                [theScrollView addSubview:image];
        UIButton *theButton = [[UIButton alloc]initWithFrame:CGRectMake(3.0f, orderOfButtons, buttonWidth, buttonHeight)];
        theButton.font = [UIFont fontWithName:@"Superclarendon-Regular" size:14];
        theButton.tag = b;
        [theButton setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
        [theButton setTag:b];
        [theButton setTitle:[menuItems objectAtIndex:b] forState:UIControlStateNormal];
        [theButton setSelected:false];
        [theButton addTarget:self action:@selector(drawerButton:) forControlEvents:UIControlEventTouchUpInside];
        
        [theScrollView addSubview:theButton];
        
        orderOfButtons += (buttonHeight + buttonSeparator);
    }
    
    [theScrollView setContentSize:CGSizeMake([theScrollView bounds].size.width, orderOfButtons + 85)];
    
    [navDrawer addSubview:theScrollView];
    
    [parentView.navigationController.view addSubview:navDrawer];
    [parentView.navigationController.navigationBar setTitleTextAttributes:
     [NSDictionary dictionaryWithObjectsAndKeys:
      [UIFont fontWithName:@"Superclarendon-Bold" size:17],
      NSFontAttributeName, nil]];
}

//Get nav item image based on index
-(UIImage *)getNavImage:(int)navIndex{
    NSString * imageName;
    switch (navIndex) {
        case 0:
            imageName = @"home-50";
            break;
        case 1:
            imageName = @"movie-50";
            break;
        case 2:
            imageName = @"calendar-50";
            break;
        case 3:
            imageName = @"search-50";
            break;
        case 4:
            imageName = @"quote-50";
            break;
        case 5:
            imageName = @"about-50";
            break;
        default:
            break;
    }
    return [UIImage imageNamed:imageName];
}

//Close drawer and push appropriate view controller
-(void)drawerButton:(UIButton *)sender
{
    
    [self swingDrawer];

    switch (sender.tag) {
        case 0:
            [self pushVC:@"Home"];
            break;
        case 1:
            [self pushVC:@"Films"];
            break;
        case 2:
            [self pushVC:@"Schedule"];
            break;
        case 3:
            [self pushVC:@"Search"];
            break;
        case 4:
            [self pushVC:@"Comments"];
            break;
        case 5:
            [self pushVC:@"About"];
            break;
        default:
            break;
    }
}

//Push view controller based on Storyboard identifier
-(void)pushVC:(NSString *) identifier
{
    NSString *restorationId = parentView.restorationIdentifier;
    if ([restorationId isEqualToString:identifier])return;
        
    UIViewController *myController = [parentView.storyboard instantiateViewControllerWithIdentifier:identifier];
    [parentView.navigationController pushViewController: myController animated:YES];
    
}

@end
