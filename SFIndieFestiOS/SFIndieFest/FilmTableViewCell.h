//
//  FilmTableViewCell.h
//  SFIndieFest
//
//  Created by eric ringer on 9/18/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface FilmTableViewCell : UITableViewCell
@property (strong, nonatomic) IBOutlet UILabel *filmTitle;
@property (strong, nonatomic) IBOutlet UIImageView *filmImage;

@end
