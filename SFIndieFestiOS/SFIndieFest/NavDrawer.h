//
//  NavDrawer.h
//  SFIndieFest
//
//  Created by Eduardo Quiroz on 9/23/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NavDrawer : NSObject


@property (nonatomic, retain) UIView *navDrawer;
@property (nonatomic, retain) UIViewController *parentView;
@property (nonatomic, retain)NSArray *menuItems;
@property (readonly, nonatomic)UISwipeGestureRecognizer *open_recognizer;
@property (readonly, nonatomic)UISwipeGestureRecognizer *close_recognizer;
@property (readonly, nonatomic)int navDrawerX, navDrawerWidth;

-(void)swingDrawer;
-(void)createDrawer;
@end
