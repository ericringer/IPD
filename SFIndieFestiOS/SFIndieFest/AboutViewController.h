//
//  AboutViewController.h
//  SFIndieFest
//
//  Created by eric ringer on 9/18/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface AboutViewController : UIViewController

@property (strong, nonatomic) IBOutlet UILabel *lblAbout;
-(IBAction)menuButton:(id)sender;
@property (strong, nonatomic) IBOutlet UIImageView *comingSoonImageView;

@end
