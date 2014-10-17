//
//  PostCommentViewController.h
//  SFIndieFest
//
//  Created by eric ringer on 10/13/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Film.h"
@interface PostCommentViewController : UIViewController
@property (strong, nonatomic) IBOutlet UITextView *commentTextView;
@property (nonatomic, retain) Film* film;

@end
